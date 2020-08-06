package com.tkouleris.coffeeshop.exception.item;

public class ItemUpdateException extends Exception {
    public String message;

    public ItemUpdateException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
