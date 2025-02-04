package com.restaurant.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.user.entity.MenuItem;
import com.restaurant.user.entity.Order;
import com.restaurant.user.exception.OrderNotFoundException;
import com.restaurant.user.exception.InvalidOrderStatusException;
import com.restaurant.user.feign.AdminServiceClient;
import com.restaurant.user.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AdminServiceClient adminServiceClient;
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<MenuItem> viewMenu() {
        return adminServiceClient.getMenu();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id);

        // Validate order status
        if (!status.equals("Accepted") && !status.equals("Preparing Food") && !status.equals("Served")) {
            throw new InvalidOrderStatusException("Invalid order status: " + status);
        }

        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
