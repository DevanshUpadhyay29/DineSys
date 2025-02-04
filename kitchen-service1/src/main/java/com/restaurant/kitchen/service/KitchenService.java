package com.restaurant.kitchen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.kitchen.entity.Order;
import com.resturant.kitchen.feign.UserServiceClient;


public interface KitchenService {

	public Order addOrder(Order order);

	public Order updateOrderStatus(Long id, String status);

	public List<Order> getPendingOrders();


	
}
