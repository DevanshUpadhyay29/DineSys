package com.restaurant.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.admin.entity.Menu;
import com.restaurant.admin.exception.MenuNotFoundException;
import com.restaurant.admin.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu addDish(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public Menu updateDish(Long id, Menu updatedMenu) {
		Optional<Menu> existingMenu = menuRepository.findById(id);
		if(existingMenu.isPresent()) {
			Menu menu = existingMenu.get();
			menu.setDishName(updatedMenu.getDishName());
			menu.setPrice(updatedMenu.getPrice());
			return menuRepository.save(menu);
		}
		throw new MenuNotFoundException("Dish not found with ID: " + id);
	}

	@Override
	public void deleteDish(Long id) {
		if (!menuRepository.existsById(id)) {
			throw new MenuNotFoundException("Dish not found with ID: " + id);
		}
		menuRepository.deleteById(id);
	}

	@Override
	public List<Menu> getAllDishes() {
		return menuRepository.findAll();
	}
}
