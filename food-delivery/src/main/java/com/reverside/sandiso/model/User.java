package com.reverside.sandiso.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	public static enum Role { USER, ADMIN }

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false, updatable = false)
	private Long id;
	private String firstname;
	private String surname;
	
	@Column(name = "email", nullable = false, unique = true)
	private String username;
	private String cellphone;
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DeliveryAddress> addressList = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Restaurants> restaurantsList;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Item> itemList;

	private boolean enabled = true;
	private String  role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DeliveryAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<DeliveryAddress> addressList) {
		this.addressList = addressList;
	}
	
	public List<Restaurants> getRestaurantsList() {
		return restaurantsList;
	}

	public void setRestaurantsList(List<Restaurants> restaurantsList) {
		this.restaurantsList = restaurantsList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;

	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}





}
