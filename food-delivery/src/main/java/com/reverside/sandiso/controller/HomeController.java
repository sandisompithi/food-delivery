package com.reverside.sandiso.controller;

import java.security.Principal;
import java.util.List;

import com.reverside.sandiso.model.DeliveryAddress;
import com.reverside.sandiso.model.User;

import org.dom4j.CDATA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.reverside.sandiso.service.DeliveryAddressService;
import com.reverside.sandiso.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DeliveryAddressService addressService;

	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();

		model.addAttribute("user", user);

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
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

	@RequestMapping("/fooddelivery")
	public String userFront(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("user", user);
		
		DeliveryAddress deliveryAddress = new DeliveryAddress();

		model.addAttribute("deliveryAddress", deliveryAddress);

		return "fooddelivery";
	}
	
	@PostMapping(value = "/deliveryAddress")
	public String deliveryAddressPost(@ModelAttribute("deliveryAddress") DeliveryAddress deliveryAddress,
									  Principal principal) {
		User user = userService.findByUsername(principal.getName());
		deliveryAddress.setUser(user);
		addressService.saveDeliveryAddress(deliveryAddress);

		return "redirect:/deliveryAddress";
	}

	@GetMapping(value = "/deliveryAddress")
	public String restaurants(Principal principal, Model model) {

		List<DeliveryAddress> addressList = addressService.findDeliveryAddressList(principal);
		model.addAttribute("addressList", addressList);

		return "deliveryAddress";
	}
}
