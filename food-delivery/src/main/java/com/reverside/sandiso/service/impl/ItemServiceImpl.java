package com.reverside.sandiso.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reverside.sandiso.model.Item;
import com.reverside.sandiso.model.Restaurants;
import com.reverside.sandiso.repository.ItemRepository;
import com.reverside.sandiso.service.ItemService;
import com.reverside.sandiso.service.RestaurantService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item findItemById(Long id) {
		return itemRepository.findItemById(id);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void displayItemPerRestaurant() {
		Restaurants restaurants = new Restaurants();
		
		if (restaurants.getItems().equals(restaurants.getId())) {
			
		}

	}

}
