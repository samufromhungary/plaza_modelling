package com.codecool.api;

public class ProductAlreadyExistsException extends ShopException {
    public ProductAlreadyExistsException() {
        super("Product already exists.");
    }
}
