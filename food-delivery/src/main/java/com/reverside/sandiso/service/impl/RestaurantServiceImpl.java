package com.reverside.sandiso.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reverside.sandiso.model.Restaurants;
import com.reverside.sandiso.model.User;
import com.reverside.sandiso.repository.RestaurantRepository;
import com.reverside.sandiso.service.RestaurantService;
import com.reverside.sandiso.service.UserService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public Restaurants saveRestaurant(Restaurants restaurants) {
		return restaurantRepository.save(restaurants);
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

	@Override
	public List<Restaurants> getRestaurantBySuburb(String username) {
		User user = userService.findByUsername(username);

		if (user != null) {
			EntityManager em = emf.createEntityManager();

			//em.getTransaction().begin();

			Query query = em.createQuery("Select " + "r.name, r.city " + "from DeliveryAddress d " +
					"inner join Restaurants r on d.suburb = r.city");

			@SuppressWarnings("unchecked")
			List<Restaurants> list = query.getResultList();


			System.out.println("Restaurant Name:");

			em.close();

			return list;
		}
		return null;
	}

}
