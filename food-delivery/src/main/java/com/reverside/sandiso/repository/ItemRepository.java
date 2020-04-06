package com.reverside.sandiso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reverside.sandiso.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	List<Item> findAll();
	Item findItemByItemId(Long id);
}
