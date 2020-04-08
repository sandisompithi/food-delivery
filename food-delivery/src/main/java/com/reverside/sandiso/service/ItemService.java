package com.reverside.sandiso.service;

import java.security.Principal;
import java.util.List;

import com.reverside.sandiso.model.Item;
import com.reverside.sandiso.model.Restaurants;

public interface ItemService {

	Item saveItem(Item item);
	List<Item> getAllItems();
	Item findItemById(Long id);
	
	void displayItemPerRestaurant(List<Restaurants> restaurants, Principal principal);
}
