package com.reverside.sandiso.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reverside.sandiso.service.ItemService;
import com.reverside.sandiso.service.RestaurantService;

@Controller
@RequestMapping("/restaurants")
public class ItemsController {
	
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ItemService itemService;
	
	@GetMapping(value = "/items")
	public String restaurants(Principal principal, Model model) {

		List<Object[]> restaurantsList = restaurantService.getRestaurantBySuburb(principal.getName());
		model.addAttribute("restaurantsList", restaurantsList);
		
		List<Object[]> itemList = itemService.getAllItems(principal);
		model.addAttribute("itemList", itemList);

		return "items";
	}

}
