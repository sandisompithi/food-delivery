package com.reverside.sandiso.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<Item> getAllItems(Principal principal) {
		String username = principal.getName();
		List<Item> itemList = itemRepository.findAll().stream()
				.filter(item -> username.equals(item.getUser().getUsername()))
				.collect(Collectors.toList());
		return itemList;
	}

	@Override
	public Item findItemById(Long id) {
		return itemRepository.findByItemId(id);
	}	

}
