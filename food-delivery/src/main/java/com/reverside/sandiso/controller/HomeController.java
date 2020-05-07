package com.reverside.sandiso.controller;

import java.security.Principal;
import java.util.List;

import com.reverside.sandiso.model.DeliveryAddress;
import com.reverside.sandiso.model.Item;
import com.reverside.sandiso.model.Restaurants;
import com.reverside.sandiso.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.reverside.sandiso.service.DeliveryAddressService;
import com.reverside.sandiso.service.ItemService;
import com.reverside.sandiso.service.RestaurantService;
import com.reverside.sandiso.service.UserService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping(value = "/signup")
	public String signup(Model model) {
		User user = new User();

		model.addAttribute("user", user);

		return "signup";
	}

	@PostMapping(value = "/signup")
	public String signupPost(@ModelAttribute("user") User user, Model model) {

		if (userService.checkUserExists(user.getUsername())) {
			model.addAttribute("usernameExists", true);
			return "signup";
		} else {
			user.setRole("USER");
			userService.createUser(user);

			return "redirect:/";
		}
	}

	@GetMapping(value = "/fooddelivery")
	public String userFront(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("user", user);

		DeliveryAddress deliveryAddress = new DeliveryAddress();

		model.addAttribute("deliveryAddress", deliveryAddress);

		return "fooddelivery";
	}
	
}
