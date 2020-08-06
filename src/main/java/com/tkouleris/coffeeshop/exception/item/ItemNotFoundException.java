package com.tkouleris.coffeeshop.exception.item;

public class ItemNotFoundException extends Exception {
    public String message;

    public ItemNotFoundException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
