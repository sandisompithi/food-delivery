package com.reverside.sandiso.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reverside.sandiso.model.Item;
import com.reverside.sandiso.model.Restaurants;
import com.reverside.sandiso.model.User;
import com.reverside.sandiso.repository.ItemRepository;
import com.reverside.sandiso.service.ItemService;
import com.reverside.sandiso.service.UserService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired 
	private UserService userService;

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
		return itemRepository.findByItemId(id);
	}

	@Override
	public void displayItemPerRestaurant(Restaurants restaurants, Principal principal) {
		 User user = userService.findByUsername(principal.getName());
		 
		 if (restaurants.getName().equalsIgnoreCase("KFC")) {
			 List<Restaurants> restaurant = user.getRestaurantsList();
			 for(Restaurants re : restaurant) {
				 re.getItems();
			 }
			 
			 itemRepository.saveAll(restaurant);
		 } else if (restaurants.getName().equalsIgnoreCase("McDonalds")) {
			 List<Restaurants> restaurant = user.getRestaurantsList();
			 for(Restaurants re : restaurant) {
				 re.getItems();
			 }
			 
			 itemRepository.saveAll(restaurant);
		 } else if (restaurants.getName().equalsIgnoreCase("Spur")) {
			 List<Restaurants> restaurant = user.getRestaurantsList();
			 for(Restaurants re : restaurant) {
				 re.getItems();
			 }
			 
			 itemRepository.saveAll(restaurant);
		 } else if (restaurants.getName().equalsIgnoreCase("Brocka")) {
			 List<Restaurants> restaurant = user.getRestaurantsList();
			 for(Restaurants re : restaurant) {
				 re.getItems();
			 }
			 
			 itemRepository.saveAll(restaurant);
		 }

	}

}
