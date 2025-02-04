package com.restaurant.kitchen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.kitchen.entity.Order;
import com.restaurant.kitchen.exception.ResourceNotFoundException;
import com.restaurant.kitchen.feign.UserServiceClient;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Autowired
    UserServiceClient userServiceClient;

    @Override
    public Order addOrder(Order order) {
        return userServiceClient.placeOrder(order);
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        Order order = userServiceClient.getOrderById(id);

        if (order == null) {
            throw new ResourceNotFoundException("Order with ID " + id + " not found");
        }

        return userServiceClient.updateOrderStatus(id, status);
    }

    @Override
    public List<Order> getPendingOrders() {
        List<Order> orders = userServiceClient.getPendingOrders();
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No pending orders found");
        }
        return orders;
    }
}
