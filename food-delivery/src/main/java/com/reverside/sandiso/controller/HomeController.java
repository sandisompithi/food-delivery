package com.reverside.sandiso.controller;

import java.security.Principal;

import com.reverside.sandiso.model.DeliveryAddress;
import com.reverside.sandiso.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.reverside.sandiso.service.UserService;

@Controller
public class HomeController {

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
