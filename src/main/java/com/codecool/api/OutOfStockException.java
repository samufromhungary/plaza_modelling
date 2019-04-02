package com.codecool.api;

public class OutOfStockException extends ShopException {
    public OutOfStockException() {
        super("Out of stock.");
    }
}
