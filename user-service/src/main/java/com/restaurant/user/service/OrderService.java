package com.restaurant.user.service;

import java.util.List;

import com.restaurant.user.entity.MenuItem;
import com.restaurant.user.entity.Order;

public interface OrderService {
	
	List<MenuItem> viewMenu();
//	Order placeOrder(Order order);
//	Order trackOrder(Long id);
//	List<Order> getPendingOrders();
	Order getOrderById(Long id);
	Order saveOrder(Order order);
	Order updateOrderStatus(Long id, String status);
	List<Order> getOrdersByStatus(String string);


}
