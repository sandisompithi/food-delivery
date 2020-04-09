package com.reverside.sandiso.service;

import java.security.Principal;
import java.util.List;

import com.reverside.sandiso.model.Item;

public interface ItemService {

	Item saveItem(Item item);
	List<Item> getAllItems(Principal principal);
	Item findItemById(Long id);
}
