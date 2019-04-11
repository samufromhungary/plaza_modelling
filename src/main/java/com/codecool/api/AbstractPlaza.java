package com.codecool.api;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlaza implements Plaza {
    protected List<Shop> shops;
    protected String name;
    protected boolean isOpen;

    public AbstractPlaza(String name, List<Shop> shops){
        this.shops = new ArrayList<Shop>();
        this.name = name;
        this.isOpen = false;
    }

    @Override
    public List<Shop> getShops() throws PlazaIsClosedException {
        if(isOpen){
            return shops;
        }
        throw new PlazaIsClosedException();
    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen){
            Shop choosenShop = null;
            for (Shop part : shops){
                if (shop.equals(part)){
                    choosenShop = shop;
                }
            }
            if (choosenShop == null){
                throw  new NoSuchShopException();
            }else{
                shops.remove(choosenShop);
            }
        }else{
            throw new PlazaIsClosedException();
        }

    }

    @Override
    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        if(isOpen){
            for(Shop choosen : shops){
                if(name.equalsIgnoreCase(choosen.getName())){
                    return choosen;
                }
            }throw new NoSuchShopException();
        }else{
            throw new PlazaIsClosedException();
        }
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void open() {
        isOpen = true;
    }

    @Override
    public void close() {
        isOpen = false;
    }

    public String getName(){
        return name;
    }
}
