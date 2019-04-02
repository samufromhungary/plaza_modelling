package com.codecool.api;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements Plaza {
    private List<Shop> shops;
    private String name;
    private boolean isOpen;

    public PlazaImpl(String name){
        this.shops = new ArrayList<Shop>();
        this.name = name;
        this.isOpen = false;
    }
    @Override
    public List<Shop> getShops() throws PlazaIsClosedException {
        if(isOpen == true){
            return shops;
        }
        throw new PlazaIsClosedException();
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        if (isOpen == true){
            try{
                findShopByName(shop.getName());
                throw new ShopAlreadyExistsException();
            }catch (NoSuchShopException e){
                shops.add(shop);
            }
        }else{
            throw new PlazaIsClosedException();
        }
    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen == true){
            Shop choosenShop = null;
            for (Shop part : shops){
                if (shop.equals(choosenShop)){
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
        if(isOpen == true){
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
