package com.codecool.api;

import java.util.List;

public class RuralPlaza extends AbstractPlaza {

    public int maxShops = 5;
    public int shopCounter = 0;


    public RuralPlaza(String name, List<Shop> shops, int maxShops) {
        super(name, shops);
        this.maxShops = maxShops;
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException, TooMuchShopsException{
        if (isOpen){
            if(getShopCounter() < maxShops){
                try{
                    findShopByName(shop.getName());
                    throw new ShopAlreadyExistsException();
                }catch (NoSuchShopException e){
                    setShopCounter(getShopCounter() + 1);
                    shops.add(shop);
                }
            }else{
                throw new TooMuchShopsException();
            }
        }else{
            throw new PlazaIsClosedException();
        }
    }

    public int getShopCounter() {
        return shopCounter;
    }

    public void setShopCounter(int shopCounter) {
        this.shopCounter = shopCounter;
    }
}
