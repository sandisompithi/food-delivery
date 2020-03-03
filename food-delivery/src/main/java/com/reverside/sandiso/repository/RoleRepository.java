package com.reverside.sandiso.repository;

import org.springframework.data.repository.CrudRepository;

import com.reverside.sandiso.model.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

	Role findByName(String name);
	
}
