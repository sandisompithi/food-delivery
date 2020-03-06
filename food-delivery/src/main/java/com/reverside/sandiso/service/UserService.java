package com.reverside.sandiso.service;

import java.util.List;

import com.reverside.sandiso.model.User;

public interface UserService {

	User findByUsername(String username);
	
	boolean checkUserExists(String username);
	
	void save(User user);
	User createUser(User user);
	User saveUser(User user);
	
	List<User> findUsersList();
	
	void enableUser(String username);
	void disableUser(String username);
}
