package com.reverside.sandiso.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.reverside.sandiso.service.impl.UserSecurityService;

@Configuration
@Order(2)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityService userSecuritySecurity;

	private static final String SALT_STRING = "salt";

	@Bean
	public BCryptPasswordEncoder bCrpytPasswordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT_STRING.getBytes()));
	}

	private static final String[] PUBLIC_MATCHER_STRINGS = { "/webjars/**", "/css/**", "/js/**", "/images/**", "/",
			"/error/**/*", "/console/**", "/register" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		// antMatchers("/**").
				antMatchers(PUBLIC_MATCHER_STRINGS).permitAll().anyRequest().authenticated();

		http.csrf().disable().cors().disable().formLogin().failureUrl("/admin/index?error").defaultSuccessUrl("/admin/restaurant")
				.loginPage("/admin/index").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout")).logoutSuccessUrl("/admin/index?logout")
				.deleteCookies("remember-me").permitAll().and().rememberMe();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecuritySecurity).passwordEncoder(bCrpytPasswordEncoder());
	}
}
