package com.reverside.sandiso.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reverside.sandiso.model.User;
import com.reverside.sandiso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/profile")
	public String profile(Principal principal, Model model) {
		
		User user = userService.findByUsername(principal.getName());
		
		model.addAttribute("user", user);
		
		return "profile";
	}
	
	@PostMapping(value = "/profile")
	public String profilePost(@ModelAttribute("user") User newUser, Model model) {
		
		User user = userService.findByUsername(newUser.getUsername());
		
		user.setUsername(newUser.getUsername());
		user.setFirstname(newUser.getFirstname());
		user.setSurname(newUser.getSurname());
		user.setCellphone(newUser.getCellphone());
		
		model.addAttribute("user", user);
		
		userService.saveUser(user);
		
		return "profile";
	}
}
