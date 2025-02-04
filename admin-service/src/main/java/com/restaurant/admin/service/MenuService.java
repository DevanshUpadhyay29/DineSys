package com.restaurant.admin.service;

import java.util.List;

import com.restaurant.admin.entity.Menu;

public interface MenuService {
	
	Menu addDish(Menu menu);
	Menu updateDish(Long id, Menu updatedMenu);
	void deleteDish(Long id);
	List<Menu> getAllDishes();
	

}
