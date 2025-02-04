package com.restaurant.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restaurant.user.entity.MenuItem;
import com.restaurant.user.entity.Order;
import com.restaurant.user.service.OrderService;

@RestController
@RequestMapping("/api/user/orders")
public class UserController {
    
    @Autowired
    private OrderService orderService;

    // View Menu
    @GetMapping("/menu")
    public List<MenuItem> viewMenu() {
        return orderService.viewMenu();
    }

    // Get Order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Place an Order
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        order.setStatus("Accepted"); // Default status
        return orderService.saveOrder(order);
    }

    // Update Order Status
    @PutMapping("/{id}")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateOrderStatus(id, status);
    }

    // Get Pending Orders
    @GetMapping("/pending")
    public List<Order> getPendingOrders() {
        return orderService.getOrdersByStatus("Accepted");
    }
}
