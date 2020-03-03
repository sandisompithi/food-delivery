package com.reverside.sandiso.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.reverside.sandiso.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	List<User> findAll();
}
