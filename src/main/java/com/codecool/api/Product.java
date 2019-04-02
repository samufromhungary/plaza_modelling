package com.codecool.api;

public abstract class Product {
    long barcode;
    String name;
    String manufacturer;

    public Product(long barcode, String name, String manufacturer){
        this.barcode = barcode;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public long getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String toString(){
        return null;
    }
}
