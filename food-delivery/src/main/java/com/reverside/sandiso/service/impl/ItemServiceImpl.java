package com.reverside.sandiso.service.impl;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reverside.sandiso.model.Item;
import com.reverside.sandiso.model.User;
import com.reverside.sandiso.repository.ItemRepository;
import com.reverside.sandiso.service.ItemService;
import com.reverside.sandiso.service.RestaurantService;
import com.reverside.sandiso.service.UserService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private UserService userService;

	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired 
	private RestaurantService restaurantService;
	
	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllItems(Principal principal) {
		
		User username = userService.findByUsername(principal.getName());
		
		if(username.equals(null)) {
			return null;
		} else {
			
			List<Object[]> restaurantList = restaurantService.getRestaurantBySuburb(principal.getName());
			

			
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			String restName = "";
			for(Object[] re : restaurantList) {
				restName = re[0].toString();
				System.out.println(restName);
				Query query = em.createQuery("Select i.restaurantName, i.itemName, i.price"
						+ " From Item i Inner Join Restaurants r"
							+ " On r.name = i.restaurantName"
							+ " Where r.name = " + restName);
				List<Object[]> list = query.getResultList();
				
				for(Object[] res : list) {
					System.out.println(res[0] + " " + res[1] + " " + res[2]);
				}
				
				return list;
				
			}
			
		}
	}

	@Override
	public Item findByRestaurantName(String name) {
		return itemRepository.findByRestaurantName(name);
	}
	
	

}
