package com.codecool.api;

public class NoSuchProductException extends ShopException {
    public NoSuchProductException() {
        super("No such product.");
    }
}
