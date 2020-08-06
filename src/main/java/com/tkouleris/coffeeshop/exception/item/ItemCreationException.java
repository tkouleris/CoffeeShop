package com.tkouleris.coffeeshop.exception.item;

public class ItemCreationException extends Exception {
    public String message;

    public ItemCreationException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
