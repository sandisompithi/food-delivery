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
	public List<Restaurants> findRestaurants(String address) {

		DeliveryAddress suburb = addressService.findDeliveryAddressBySuburb(address);
				
		if (suburb.getSuburb().equalsIgnoreCase("Century City")) {
			
			List<Restaurants> restaurantsList = restaurantRepository.findAll();
			
			return restaurantsList;
			
		} else if (suburb.getSuburb().equalsIgnoreCase("Cape Town")) {
			
			List<Restaurants> restaurantsList = restaurantRepository.findAll();
			
			return restaurantsList;
			
		} else if (suburb.getSuburb().equalsIgnoreCase("Khayelitsha")) {
			
			List<Restaurants> restaurantsList = restaurantRepository.findAll();
			
			return restaurantsList;
			
		} else {
			
			return null;
		}
	}

	@Override
	public Restaurants findRestaurant(String name) {
		return restaurantRepository.findByName(name);
	}

}
