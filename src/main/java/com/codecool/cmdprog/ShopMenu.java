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

    public void list(PlazaImpl plaza){
        try{
            list = plaza.getShops();
            for (Shop shop: list) {
                System.out.println(shop.toString());
            }
            if(list == null){
                System.out.println("This plaza doesn't have any shops right now.");
            }
        }catch (PlazaIsClosedException e){
            System.out.println(e);
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
            int shopInput = sc.nextInt();
            switch (shopInput){
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
                    long barcodeOfProduct = sc.nextLong();
                    System.out.println("Give it a name:");
                    String nameOfAddedProduct = sc.nextLine();
                    System.out.println("Give it a quantity:");
                    int quantityOfProduct = sc.nextInt();
                    System.out.println("Give it a manufacturer:");
                    String manufacturerOfProduct = sc.nextLine();
                    System.out.println("Give it a price:");
                    int priceOfProduct = sc.nextInt();
                    System.out.println("What type of product you want to generate? Food, or Cloth?");
                    String typeOfProduct = sc.nextLine();
                    if (typeOfProduct.equalsIgnoreCase("Food")){
                        System.out.println("How much calories it has?:");
                        int caloriesOfProduct = sc.nextInt();
                        System.out.println("Safe to consume until?: (EG: 9999.99.99");
                        String dateOfProduct = sc.nextLine();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                        try{
                            Date date = format.parse(dateOfProduct);
                            product = new FoodProduct(barcodeOfProduct, nameOfAddedProduct, manufacturerOfProduct, caloriesOfProduct, date);
                        }catch (ParseException e){
                            e.getStackTrace();
                        }
                    }else if(typeOfProduct.equalsIgnoreCase("Cloth")){
                        System.out.println("Material:");
                        String materialOfProduct = sc.nextLine();
                        System.out.println("Type:");
                        String typeOfClothProduct = sc.nextLine();
                        product = new ClothingProduct(barcodeOfProduct, nameOfAddedProduct, manufacturerOfProduct, materialOfProduct, typeOfClothProduct);
                    }
                    try{
                        shop.addNewProduct(product, quantityOfProduct, priceOfProduct);
                    }catch (ProductAlreadyExistsException | ShopIsClosedException e){
                        System.out.println(e);
                    }
                    break;
                case 7:
                    System.out.println("Barcode:");
                    long chosenBarcode = sc.nextLong();
                    System.out.println("Quantity:");
                    int chosenQuantity = sc.nextInt();
                    try{
                        shop.addProduct(chosenBarcode, chosenQuantity);
                    }catch (NoSuchProductException | ShopIsClosedException e){
                        System.out.println(e);
                    }
                    break;
                case 8:
                    System.out.println("Bye!");
                    System.exit(-1);
                    break;
            }
        }
    }


}
