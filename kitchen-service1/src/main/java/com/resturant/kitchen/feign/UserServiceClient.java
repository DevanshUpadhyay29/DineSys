package com.resturant.kitchen.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import com.restaurant.kitchen.model.Order;

@FeignClient(name= "user-service")    	//Calls User Service via Eureka
public interface UserServiceClient {
	
	List<Order> getAllPendingOrders();

	com.restaurant.kitchen.entity.Order placeOrder(com.restaurant.kitchen.entity.Order order);


}
