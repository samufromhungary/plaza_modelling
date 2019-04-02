package com.codecool.api;

public class PlazaIsClosedException extends ShopException {
    public PlazaIsClosedException() {
        super("Plaza is closed.");
    }
}
