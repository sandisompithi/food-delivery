package com.reverside.sandiso.service;

import java.security.Principal;
import java.util.List;

import com.reverside.sandiso.model.DeliveryAddress;
import com.reverside.sandiso.model.Restaurants;

public interface RestaurantService {
	Restaurants saveRestaurant(Restaurants restaurants);
	List<Restaurants> findRestaurants(Principal principal);
	Restaurants findRestaurant(String name);
	List<Object[]> getRestaurantBySuburb(String username);
}
