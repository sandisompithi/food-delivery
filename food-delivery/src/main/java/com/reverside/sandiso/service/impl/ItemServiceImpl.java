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
	private EntityManagerFactory emf;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllItems(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		List<Object[]> lists = restaurantService.getRestaurantBySuburb(principal.getName());
		
		for (Object[] li : lists) {
			if (li[0].equals("KFC")) {
				
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();

				Query query = em.createQuery("Select r.name, i.itemName, i.price from Item i "
						+ "join Restaurants r on i.restaurantName = r.name "
						+ "join DeliveryAddress d on r.city = d.suburb "  
						+ "join User u on d.user = u.id where u.id = " + user.getId()
						+ " and i.restaurantName = '" + li[0] + "'"
						+ " Group by i.itemName " + "Order by i.itemName");
				List<Object[]> list = query.getResultList();
				for (Object[] res : list) {
					System.out.println(res[0] + " " + res[1] + " " + res[2]);
				}
				return list;
			} else if (li[0].equals("Rocco_Mamas")) {
				
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();

				Query query = em.createQuery("Select r.name, i.itemName, i.price from Item i "
						+ "join Restaurants r on i.restaurantName = r.name "
						+ "join DeliveryAddress d on r.city = d.suburb "  
						+ "join User u on d.user = u.id where u.id = " + user.getId()
						+ " and i.restaurantName = '" + li[0] + "'"
						+ " Group by i.itemName " + "Order by i.itemName");
				List<Object[]> list = query.getResultList();
				for (Object[] res : list) {
					System.out.println(res[0] + " " + res[1] + " " + res[2]);
				}
				return list;
			} else if (li[0].equals("McDonalds")) {
				
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();

				Query query = em.createQuery("Select r.name, i.itemName, i.price from Item i "
						+ "join Restaurants r on i.restaurantName = r.name "
						+ "join DeliveryAddress d on r.city = d.suburb "  
						+ "join User u on d.user = u.id where u.id = " + user.getId()
						+ " and i.restaurantName = '" + li[0] + "'"
						+ " Group by i.itemName " + "Order by i.itemName");
				List<Object[]> list = query.getResultList();
				for (Object[] res : list) {
					System.out.println(res[0] + " " + res[1] + " " + res[2]);
				}
				return list;
			} else if (li[0].equals("Spur")) {
				
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();

				Query query = em.createQuery("Select r.name, i.itemName, i.price from Item i "
						+ "join Restaurants r on i.restaurantName = r.name "
						+ "join DeliveryAddress d on r.city = d.suburb "  
						+ "join User u on d.user = u.id where u.id = " + user.getId()
						+ " and i.restaurantName = '" + li[0] + "'"
						+ " Group by i.itemName " + "Order by i.itemName");
				List<Object[]> list = query.getResultList();
				for (Object[] res : list) {
					System.out.println(res[0] + " " + res[1] + " " + res[2]);
				}
				return list;
			} else if (li[0].equals("Panarotis")) {
				
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();

				Query query = em.createQuery("Select r.name, i.itemName, i.price from Item i "
						+ "join Restaurants r on i.restaurantName = r.name "
						+ "join DeliveryAddress d on r.city = d.suburb "  
						+ "join User u on d.user = u.id where u.id = " + user.getId()
						+ " and i.restaurantName = '" + li[0] + "'"
						+ " Group by i.itemName " + "Order by i.itemName");
				List<Object[]> list = query.getResultList();
				for (Object[] res : list) {
					System.out.println(res[0] + " " + res[1] + " " + res[2]);
				}
				return list;
			}
		}
		return null;
	}

	@Override
	public Item findByRestaurantName(String name) {
		return itemRepository.findByRestaurantName(name);
	}

}
