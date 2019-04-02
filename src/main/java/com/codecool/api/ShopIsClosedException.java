package com.codecool.api;

public class ShopIsClosedException extends ShopException {
    public ShopIsClosedException() {
        super("Shop is closed.");
    }
}
