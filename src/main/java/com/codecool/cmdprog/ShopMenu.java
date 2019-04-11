package com.codecool.cmdprog;

import com.codecool.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShopMenu {
    List<Shop> list = null;
    Scanner sc = new Scanner(System.in);

    public ShopMenu(){

    }

    public void listShops(PlazaImpl plaza){
        if(plaza.isOpen()){
            try{
                if(plaza.getShops().isEmpty()){
                    System.out.println("Plaza is empty.");
                }else{
                    list = plaza.getShops();
                    for (Shop shop: list){
                        System.out.println(shop.toString());
                    }
                }
            }catch (PlazaIsClosedException e){
                System.out.println(e);
            }
        }
    }

    public void listProducts(List<Product> products){
        for (Product product : products){
            if (product instanceof ClothingProduct){
                System.out.println(product.toString());
            }else if(product instanceof FoodProduct && ((FoodProduct) product).isStillConsumable()){
                System.out.println(product.toString());
            }
        }
    }

    public void menuList(Shop shop){
        while(true){
            new Menu(shop.getName(), new String[]{"Check Shop Status", "Open / Close Shop", "List products", "Find a product by name", "Buy product", "Add new product", "Add to an existing product", "Exit"});
            String shopInput = sc.nextLine();
            switch (Integer.valueOf(shopInput)){
                case 1 :
                    if(shop.isOpen()){
                        System.out.println("Shop is open right now.");
                    }else{
                        System.out.println("Shop is closed right now.");
                    }break;
                case 2 :
                    if (shop.isOpen()){
                        shop.close();
                        System.out.println("Shop closed.");
                    }else{
                        shop.open();
                        System.out.println("Shop is opened.");
                    }break;
                case 3 :
                    try{
                        listProducts(shop.getProducts());
                    }catch (ShopIsClosedException e){
                        System.out.println(e);
                    }break;
                case 4 :
                    System.out.println("What are you searching for?");
                    String searchedProduct = sc.nextLine();
                    try{
                        System.out.println(shop.findByName(searchedProduct).toString());
                    }catch(ShopIsClosedException | NoSuchProductException e){
                        System.out.println(e);
                    }break;
                case 5 :
                    System.out.println("What are you searching for?:");
                    String nameOfProduct = sc.nextLine();
                    try{
                        for (Product product : shop.getProducts()) {
                            if(product.getName().equalsIgnoreCase(nameOfProduct)){
                                shop.buyProduct(product.getBarcode());
                                System.out.println("Successfully purcashed.");
                            }
                        }
                    }catch (ShopIsClosedException |NoSuchProductException | OutOfStockException e){
                        System.out.println(e);
                    }break;
                case 6 :
                    Product product = null;
                    System.out.println("Give it a barcode:");
                    String barcodeOfProduct = sc.nextLine();
                    System.out.println("Give it a name:");
                    String nameOfAddedProduct = sc.nextLine();
                    System.out.println("Give it a quantity:");
                    String quantityOfProduct = sc.nextLine();
                    System.out.println("Give it a manufacturer:");
                    String manufacturerOfProduct = sc.nextLine();
                    System.out.println("Give it a price:");
                    String priceOfProduct = sc.nextLine();
                    System.out.println("What type of product you want to generate? Food, or Cloth?");
                    String typeOfProduct = sc.nextLine();
                    if (typeOfProduct.equalsIgnoreCase("Food")){
                        System.out.println("How much calories it has?:");
                        String caloriesOfProduct = sc.nextLine();
                        System.out.println("Safe to consume until?: (EG: 9999.99.99");
                        String dateOfProduct = sc.nextLine();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                        try{
                            Date date = format.parse(dateOfProduct);
                            product = new FoodProduct(Long.valueOf(barcodeOfProduct), nameOfAddedProduct, manufacturerOfProduct, Integer.valueOf(caloriesOfProduct), date);
                        }catch (ParseException e){
                            e.getStackTrace();
                        }
                    }else if(typeOfProduct.equalsIgnoreCase("Cloth")){
                        System.out.println("Material:");
                        String materialOfProduct = sc.nextLine();
                        System.out.println("Type:");
                        String typeOfClothProduct = sc.nextLine();
                        product = new ClothingProduct(Long.valueOf(barcodeOfProduct), nameOfAddedProduct, manufacturerOfProduct, materialOfProduct, typeOfClothProduct);
                    }
                    try{
                        shop.addNewProduct(product, Integer.valueOf(quantityOfProduct), Integer.valueOf(priceOfProduct));
                    }catch (ProductAlreadyExistsException | ShopIsClosedException e){
                        System.out.println(e);
                    }
                    break;
                case 7:
                    System.out.println("Barcode:");
                    String chosenBarcode = sc.nextLine();
                    System.out.println("Quantity:");
                    String chosenQuantity = sc.nextLine();
                    try{
                        shop.addProduct(Long.valueOf(chosenBarcode), Integer.valueOf(chosenQuantity));
                    }catch (NoSuchProductException | ShopIsClosedException e){
                        System.out.println(e);
                    }
                    break;
                case 8:
                    System.out.println("Bye!");
                    return;
            }
        }
    }


}
