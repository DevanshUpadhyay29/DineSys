package com.restaurant.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.admin.entity.Menu;
import com.restaurant.admin.exception.MenuNotFoundException;
import com.restaurant.admin.service.MenuService;

@RestController
@RequestMapping("/api/admin")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@PostMapping
	public ResponseEntity<Menu> addDish(@RequestBody Menu menu){
		Menu createdMenu = menuService.addDish(menu);
		return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Menu> updateDish(@PathVariable Long id, @RequestBody Menu updatedMenu) {
		try {
			Menu menu = menuService.updateDish(id, updatedMenu);
			return new ResponseEntity<>(menu, HttpStatus.OK);
		} catch (MenuNotFoundException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDish(@PathVariable Long id){
		try {
			menuService.deleteDish(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (MenuNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Menu>> getAllDishes(){
		List<Menu> menuList = menuService.getAllDishes();
		return new ResponseEntity<>(menuList, HttpStatus.OK);
	}
}
