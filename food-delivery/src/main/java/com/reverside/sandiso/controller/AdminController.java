package com.reverside.sandiso.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.reverside.sandiso.model.Item;
import com.reverside.sandiso.model.Restaurants;
import com.reverside.sandiso.model.User;
import com.reverside.sandiso.service.ItemService;
import com.reverside.sandiso.service.RestaurantService;
import com.reverside.sandiso.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
 
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping(value = "/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}
	
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
    
    @GetMapping(value = "/restaurant")
	public String restaurant(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		Restaurants restaurants = new Restaurants();
		model.addAttribute("restaurants", restaurants);

		return "restaurant";
	}

	@PostMapping(value = "/restaurant")
	public String restaurantPost(@ModelAttribute("restaurant") Restaurants restaurants, Principal principal,
			@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

		User user = userService.findByUsername(principal.getName());

		try {
			logger.info("Name= ", name);

			byte[] image = file.getBytes();

			restaurants.setImage(image);
			restaurants.setUser(user);
			restaurantService.saveRestaurant(restaurants);
			return "redirect:/admin/restaurant";

		} catch (Exception e) {
			logger.error("ERROR", e);
			return null;
		}

	}
    
    @GetMapping(value = "/addItem")
    public String addItem(Model model, Principal principal) {
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	
    	Item item = new Item();
    	model.addAttribute("item", item);
    	
    	return "addItem";
    }
    
    
    @PostMapping(value = "/addItem/save")
    public String addItemPost(@ModelAttribute("item") Item item, Principal principal) {
    	
    	User user = userService.findByUsername(principal.getName());
    	
    	item.setUser(user);
    	itemService.saveItem(item);
    
    	return "redirect:/admin/addItem";
    }
}
