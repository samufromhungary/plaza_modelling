package com.codecool.api;

public class ShopAlreadyExistsException extends ShopException {
    public ShopAlreadyExistsException() {
        super("Already existing shop.");
    }
}
