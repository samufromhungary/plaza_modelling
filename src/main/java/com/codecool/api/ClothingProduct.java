package com.codecool.api;

public class ClothingProduct extends Product {
    String material;
    String type;

    public ClothingProduct(long barcode, String name, String manufacturer, String material, String type) {
        super(barcode, name, manufacturer);
    }

    public String getMaterial() {
        return material;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return "Name: " + getName() + " | Manufacturer: " + getManufacturer() + " | Material: " + getMaterial() + " | Barcode: " + getBarcode();
    }
}
