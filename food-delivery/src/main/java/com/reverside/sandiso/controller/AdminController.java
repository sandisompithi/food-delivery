package com.reverside.sandiso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reverside.sandiso.model.User;
import com.reverside.sandiso.service.RestaurantService;
import com.reverside.sandiso.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
 
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/register")
    public String adminReg(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping(value = "/register")
    public String adminReg(@ModelAttribute("user") User user, Model model) {

        if (userService.checkUserExists(user.getUsername())) {
            model.addAttribute("usernameExists", true);
            return "register";
        } else {
            user.setRole("ADMIN");
            userService.createUser(user);

            return "redirect:/";
        }
    }
}