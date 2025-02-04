package com.restaurant.user.exception;

public class InvalidOrderStatusException extends UserServiceException {
    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
