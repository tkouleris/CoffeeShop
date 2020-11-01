package com.tkouleris.coffeeshop.exception.order;

public class OrderNotFoundException extends Exception{
    public String message;

    public OrderNotFoundException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
