package com.reverside.sandiso.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.reverside.sandiso.service.impl.UserSecurityService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private UserSecurityService userSecuritySecurity;

	private static final String SALT_STRING = "salt";

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT_STRING.getBytes()));
	}

	private static final String[] PUBLIC_MATCHER_STRINGS = { "/webjars/**", "/css/**", "/js/**", "/images/**", "/",
			"/error/**/*", "/console/**", "/signup" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		// antMatchers("/**").
				antMatchers(PUBLIC_MATCHER_STRINGS).permitAll().anyRequest().authenticated();

		http.csrf().disable().cors().disable().formLogin().failureUrl("/index?error").defaultSuccessUrl("/fooddelivery")
				.loginPage("/index").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout")
				.deleteCookies("remember-me").permitAll().and().rememberMe();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecuritySecurity).passwordEncoder(passwordEncoder());
	}
}
