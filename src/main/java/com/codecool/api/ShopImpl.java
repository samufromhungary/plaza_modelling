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
        if (isOpen){
            List<Product> productlist = new ArrayList<Product>();
            for(ShopEntryImpl iter : products.values()){
                if(iter.getQuantity() >= 1){
                    productlist.add(iter.getProduct());
                }
            }return productlist;
        }else{
            throw new ShopIsClosedException();
        }
    }

    @Override
    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        if(isOpen){
            for (ShopEntryImpl iter : products.values()){
                if(name.equalsIgnoreCase(iter.getProduct().getName())){
                    return iter.getProduct();
                }
            }
            throw new NoSuchProductException();
        }else{
            throw new ShopIsClosedException();
        }
    }

    @Override
    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {
        if(isOpen){
            ShopEntryImpl barcImpl = products.get(barcode);
            if(barcImpl == null){
                throw new NoSuchProductException();
            }
            return barcImpl.getPrice();
        }else{
            throw new ShopIsClosedException();
        }
    }

    @Override
    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        if(isOpen){
            ShopEntryImpl barcImpl = products.get(barcode);
            if(barcImpl != null){
                return true;
            }
            return false;
        }else{
            throw new ShopIsClosedException();
        }
    }

    @Override
    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
        if(isOpen){
            if(products.containsKey(product.getBarcode())){
                throw new ProductAlreadyExistsException();
            }else{
                products.put(product.getBarcode(), new ShopEntryImpl(product, quantity, price));
            }
        }else{
            throw new ShopIsClosedException();
        }
    }

    @Override
    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {
        if(isOpen){
            if(hasProduct(barcode)){
                for (ShopEntryImpl iter : products.values()){
                    if (barcode == iter.getProduct().getBarcode()){
                        iter.increase(quantity);
                    }
                }
            }else if(!hasProduct(barcode)){
                throw new NoSuchProductException();
            }
        }else{
            throw new ShopIsClosedException();
        }
    }

    @Override
    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        if(isOpen){
            if(products.containsKey(barcode)){
                for(ShopEntryImpl iter : products.values()){
                    if(barcode == iter.getProduct().getBarcode()){
                        if(iter.getQuantity() == 0){
                            throw new OutOfStockException();
                        }else{
                            iter.decrease(1);
                            return iter.getProduct();
                        }
                    }
                }
            }
            throw new NoSuchProductException();
        }else{
            throw new ShopIsClosedException();
        }
    }

    @Override
    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        if(isOpen){
            List<Product> productlist = new ArrayList<Product>();
            if (products.containsKey(barcode)){
                for(ShopEntryImpl iter : products.values()){
                    if(barcode == iter.getProduct().getBarcode()){
                        if(iter.getQuantity() == 0){
                            throw new OutOfStockException();
                        }else{
                            iter.decrease(quantity);
                            productlist.add(iter.getProduct());
                        }
                    }
                }return productlist;
            }else{
                throw new NoSuchProductException();
            }
        }else{
            throw new ShopIsClosedException();
        }
    }

    public String toString(){
        return "Name: " + name + " | Owner: " + owner;
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

        public void increase(int amount){
            quantity += amount;
        }

        public void decrease(int amount){
            quantity -= amount;
        }

    }
}
