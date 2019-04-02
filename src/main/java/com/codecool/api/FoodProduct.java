package com.codecool.api;

import java.util.Date;

public class FoodProduct extends Product {
    int calories;
    Date bestBefore;

    public FoodProduct(long barcode, String name, String manufacturer, int calories, Date bestBefore) {
        super(barcode, name, manufacturer);
    }

    public boolean isStillConsumable(){
        return false;
    }

    public int getCalories() {
        return calories;
    }

    public String toString(){
        return null;
    }

}
