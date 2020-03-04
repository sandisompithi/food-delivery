package com.reverside.sandiso.service;

import java.util.List;
import java.util.Set;

import com.reverside.sandiso.model.User;
import com.reverside.sandiso.model.security.UserRole;

public interface UserService {

	User findByUsername(String username);
	
	boolean checkUserExists(String username);
	
	void save(User user);
	User createUser(User user, Set<UserRole> userRoles);
	User saveUser(User user);
	
	List<User> findUsersList();
	
	void enableUser(String username);
	void disableUser(String username);
}
