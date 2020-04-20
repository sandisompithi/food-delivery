package com.reverside.sandiso.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reverside.sandiso.model.DeliveryAddress;
import com.reverside.sandiso.model.Restaurants;
import com.reverside.sandiso.model.User;
import com.reverside.sandiso.repository.DeliveryAddressRepository;
import com.reverside.sandiso.repository.RestaurantRepository;
import com.reverside.sandiso.service.RestaurantService;
import com.reverside.sandiso.service.UserService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	 private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private DeliveryAddressRepository deliveryAddressRepo;
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private EntityManagerFactory emf;
	
	@Override
	public Restaurants saveRestaurant(Restaurants restaurants) {
		try {
			return restaurantRepository.save(restaurants);
		} catch (Exception e) {
			logger.error("ERROR", e);
			return null;
		}
	}

	@Override
	public List<Restaurants> findRestaurants(Principal principal) {

		String username = principal.getName();
		List<Restaurants> restaurantsList = restaurantRepository.findAll().stream()
				.filter(restaurant -> username.equals(restaurant.getUser().getUsername()))
				.collect(Collectors.toList());
		return restaurantsList;
	}

	@Override
	public Restaurants findRestaurant(String name) {
		return restaurantRepository.findByName(name);
	}
	
	/*
	 * public List<Object[]> getRestaurantBySuburb(String username) { User user =
	 * userService.findByUsername(username);
	 * 
	 * if (user != null) { EntityManager em = emf.createEntityManager();
	 * 
	 * //em.getTransaction().begin();
	 * 
	 * Query query = em.createQuery("Select r.name, r.city from Restaurants r " +
	 * "join DeliveryAddress d on r.city = d.suburb " +
	 * "join User u on d.user = u.id where u.id = " + user.getId() +
	 * " group by r.name order by r.name");
	 * 
	 * @SuppressWarnings("unchecked") List<Object[]> list = query.getResultList();
	 * list.stream().collect(Collectors.toList());
	 * 
	 * for (Object[] lst : list) { System.out.println("Restaurant Name:" + lst[0] +
	 * " " + lst[1]); } em.close(); return list; } else { return null; } }
	 */
	
	public List<Restaurants> getRestaurantBySuburb(Principal principal) {
		
		User username = userService.findByUsername(principal.getName());
		
		if(username != null) {
			List<Restaurants> restaurantList = restaurantRepository.findAll();
			List<DeliveryAddress> addressList = deliveryAddressRepo.findAll();
			List<Restaurants> list = null;
			for (DeliveryAddress suburb : addressList) {
				for (Restaurants city : restaurantList) {
					if (suburb.getSuburb().equals(city.getCity())) {
						list =  restaurantList;
					}
				}
			}
			return list;
		} else {
			return null;
		}
		
	}
}
