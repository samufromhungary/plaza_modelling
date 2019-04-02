package com.codecool.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {
    private String name;
    private String owner;
    private Map<Long, ShopEntryImpl> products;
    private boolean isOpen;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.products = new HashMap<Long, ShopEntryImpl>();
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public boolean isOpen() { return isOpen; }

    @Override
    public void open() {
        isOpen = true;

    }

    @Override
    public void close() {
        isOpen = false;
    }

    @Override
    public List<Product> getProducts() throws ShopIsClosedException {
        if (isOpen == true){

        }
    }

    @Override
    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        return null;
    }

    @Override
    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {
        return 0;
    }

    @Override
    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        return false;
    }

    @Override
    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {

    }

    @Override
    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {

    }

    @Override
    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    @Override
    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    public String toString(){
        return null;
    }

    private class ShopEntryImpl{
        Product product;
        int quantity;
        float price;

        private ShopEntryImpl(Product product, int quantity, float price){
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

    }
}
