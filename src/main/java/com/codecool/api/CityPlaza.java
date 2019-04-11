package com.codecool.api;

import java.util.List;

public class CityPlaza extends AbstractPlaza {

    private int yearly_Revenue = 5000;

    public CityPlaza(String name, List<Shop> shops, int yearly_Revenue) {
        super(name, shops);
        this.yearly_Revenue = yearly_Revenue;
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException, YearlyRevenueIsTooHighException{
        if (isOpen){
            if(shop.getYearlyRevenue() >= yearly_Revenue){
                try{
                    findShopByName(shop.getName());
                    throw new ShopAlreadyExistsException();
                }catch (NoSuchShopException e){
                    shops.add(shop);
                }
            }else{
                throw new YearlyRevenueIsTooHighException();
            }
        }else{
            throw new PlazaIsClosedException();
        }
    }
}
