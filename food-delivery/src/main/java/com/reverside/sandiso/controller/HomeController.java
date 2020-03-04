package com.reverside.sandiso.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.reverside.sandiso.model.User;
import com.reverside.sandiso.model.security.UserRole;
import com.reverside.sandiso.repository.RoleRepository;
import com.reverside.sandiso.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@GetMapping(value = { "/", "/fooddelivery"})
	public String fooddelivery(Model model) {
		return "fooddelivery";
	}
	
	@GetMapping(value = "/signup")
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}
	
	@PostMapping(value = "/signup")
	public String signup(@ModelAttribute("user") User user, Model model) {
		
		if (userService.checkUserExists(user.getUsername())) {
			model.addAttribute("usernameExists", true);
			return "signup";
		} else {
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(user, roleRepository.findByName("ROLE_USER")));
			userService.createUser(user, userRoles);
			return "redirect:/fooddelivery";
		}
	}
	
	@GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
	
	@GetMapping(value = "/fooddelivery")
	public String foodDelivery(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "fooddelivery";
	}
}
