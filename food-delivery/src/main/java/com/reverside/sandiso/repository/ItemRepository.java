package com.reverside.sandiso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reverside.sandiso.model.Item;
import com.reverside.sandiso.model.Restaurants;

public interface ItemRepository extends JpaRepository<Item, Long>{
	
	List<Item> findAll();
	Item findByRestaurantName(String name);
	void save(List<Restaurants> restaurant);
}
