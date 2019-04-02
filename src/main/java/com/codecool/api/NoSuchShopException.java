package com.codecool.api;

public class NoSuchShopException extends ShopException {
    public NoSuchShopException() {
        super("No such shop.");
    }
}
