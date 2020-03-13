package com.reverside.sandiso.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.reverside.sandiso.model.Restaurants;

public interface RestaurantRepository extends CrudRepository<Restaurants, Long> {
	List<Restaurants> findAll();
	Restaurants findByName(String name);
}
