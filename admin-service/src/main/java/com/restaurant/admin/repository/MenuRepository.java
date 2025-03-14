package com.restaurant.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.admin.entity.Menu;



public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	public Menu findByDishName(String requestedDish);
}
