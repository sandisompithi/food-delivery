package com.reverside.sandiso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reverside.sandiso.model.Item;
import com.reverside.sandiso.model.Restaurants;

public interface ItemRepository extends JpaRepository<Item, Long>{
	
	@Query("Select i.restaurantName, i.itemName, i.price"
			+ " From Item i Inner Join Restaurants r"
			+ " On r.name = i.restaurantName")
	List<Item> findAll();
	Item findByRestaurantName(String name);
	void save(List<Restaurants> restaurant);
}
