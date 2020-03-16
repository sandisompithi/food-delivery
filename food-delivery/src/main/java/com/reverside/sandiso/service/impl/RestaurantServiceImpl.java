package com.reverside.sandiso.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reverside.sandiso.model.DeliveryAddress;
import com.reverside.sandiso.model.Restaurants;
import com.reverside.sandiso.repository.RestaurantRepository;
import com.reverside.sandiso.service.DeliveryAddressService;
import com.reverside.sandiso.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private DeliveryAddressService addressService;
	
	@Override
	public Restaurants saveRestaurant(Restaurants restaurants) {
		return restaurantRepository.save(restaurants);
	}

	@Override
	public List<Restaurants> findRestaurants() {

		return restaurantRepository.findAll();
	}

	@Override
	public Restaurants findRestaurant(String name) {
		return restaurantRepository.findByName(name);
	}

}
