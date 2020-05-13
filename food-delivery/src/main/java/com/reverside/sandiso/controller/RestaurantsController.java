package com.reverside.sandiso.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reverside.sandiso.model.DeliveryAddress;
import com.reverside.sandiso.model.User;
import com.reverside.sandiso.service.DeliveryAddressService;
import com.reverside.sandiso.service.ItemService;
import com.reverside.sandiso.service.RestaurantService;
import com.reverside.sandiso.service.UserService;

@Controller
@RequestMapping("/restaurants")
public class RestaurantsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeliveryAddressService addressService;
	
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ItemService itemService;
	
	@PostMapping(value = "/location")
	public String deliveryAddressPost(@ModelAttribute("location") DeliveryAddress deliveryAddress,
			Principal principal) {
		User user = userService.findByUsername(principal.getName());
		deliveryAddress.setUser(user);
		addressService.saveDeliveryAddress(deliveryAddress);

		return "redirect:/restaurants/location";
	}

	@GetMapping(value = "/location")
	public String restaurants(Principal principal, Model model) {

		List<DeliveryAddress> addressList = addressService.findDeliveryAddressList(principal);
		model.addAttribute("addressList", addressList);

		List<Object[]> restaurantsList = restaurantService.getRestaurantBySuburb(principal.getName());
		model.addAttribute("restaurantsList", restaurantsList);

		return "location";
	}

}
