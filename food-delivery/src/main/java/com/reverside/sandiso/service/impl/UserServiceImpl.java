package com.reverside.sandiso.service.impl;

import java.util.List;

import com.reverside.sandiso.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.reverside.sandiso.model.User;
import com.reverside.sandiso.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean checkUserExists(String username) {
		if (null != findByUsername(username)) {
			return true;
		}
		return false;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User createUser(User user) {
		
		User localUser = userRepository.findByUsername(user.getSurname());
		
		if (localUser != null) {
			log.info("User with username {} already exist. Nothing will be done. ", user.getSurname());
		} else {
			String encryptedPasswordString = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPasswordString);
			
			localUser = userRepository.save(user);
		}
		return localUser;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findUsersList() {
		return userRepository.findAll();
	}

	@Override
	public void enableUser(String username) {
		User user = findByUsername(username);
		user.setEnabled(true);
		userRepository.save(user);
	}

	@Override
	public void disableUser(String username) {
		User user = findByUsername(username);
		user.setEnabled(false);
		System.out.println(user.isEnabled());
		userRepository.save(user);
		System.out.println(username + " is disabled.");
	}

}
