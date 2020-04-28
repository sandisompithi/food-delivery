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
		
		List<Object[]> ddd = restaurantService.getRestaurantBySuburb(principal.getName());
		Object ee = null;
		for (Object[] ff : ddd) {
			ee = ff[0];
		}
		System.out.println("The name is " + ee);
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select r.name, i.itemName, i.price from Item i "
				+ "join Restaurants r on i.restaurantName = r.name "
					+ "join DeliveryAddress d on r.city = d.suburb "
				+ " where r.name = " + ee
					+ " Group by i.itemName " + " i.itemName");
		List<Object[]> list = query.getResultList();
		
		for(Object[] res : list) {
			System.out.println(res[0] + " " + res[1] + " " + res[2]);
		}
		
		return list;
	}

	@Override
	public Item findByRestaurantName(String name) {
		return itemRepository.findByRestaurantName(name);
	}
	
	

}
