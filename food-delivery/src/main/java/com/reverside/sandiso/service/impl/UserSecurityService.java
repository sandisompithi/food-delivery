package com.reverside.sandiso.service.impl;

import com.reverside.sandiso.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reverside.sandiso.model.User;

@Service
public class UserSecurityService implements UserDetailsService{
	
	/** The application logger */
	private static final Logger log = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			log.warn("Username {} not found", username);
			throw new UsernameNotFoundException("Username" + username + "not found");
		}
		return user;
	}

}
