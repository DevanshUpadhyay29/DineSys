package com.restaurant.user.exception;

public class OrderNotFoundException extends UserServiceException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
