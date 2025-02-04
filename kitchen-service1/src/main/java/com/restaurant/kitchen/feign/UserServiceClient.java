package com.restaurant.kitchen.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurant.kitchen.entity.Order;

@FeignClient(name = "user-service", path = "/api/user/orders")
public interface UserServiceClient {
	
	@PostMapping
	Order placeOrder(@RequestBody Order order);

	@GetMapping("/pending")
	List<Order> getPendingOrders();
	
	@GetMapping("/{id}")
	Order getOrderById(@PathVariable Long id);
	
	@PutMapping("/{id}")
	Order updateOrderStatus(@PathVariable Long id,@RequestParam String status);
	
}
