package com.codecool.api;

public class TooMuchShopsException extends ShopException {
    public TooMuchShopsException() {
        super("Plaza has too much shops to add another one.");
    }
}
