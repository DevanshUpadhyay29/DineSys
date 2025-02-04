package com.restaurant.kitchen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.kitchen.entity.Order;
import com.restaurant.kitchen.exception.ResourceNotFoundException;
import com.restaurant.kitchen.service.KitchenService;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {

    @Autowired
    KitchenService kitchenService;

    // Add a New Order
    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return new ResponseEntity<>(kitchenService.addOrder(order), HttpStatus.CREATED);
    }

    // Update Order Status
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updatedOrderStatus(@PathVariable Long id, @RequestBody String status) {
        Order updatedOrder = kitchenService.updateOrderStatus(id, status);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    // Get Pending Orders
    @GetMapping("/pending-orders")
    public ResponseEntity<List<Order>> getPendingOrders() {
        List<Order> orders = kitchenService.getPendingOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Handle ResourceNotFoundException explicitly
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
