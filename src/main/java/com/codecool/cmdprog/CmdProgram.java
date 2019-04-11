package com.codecool.cmdprog;

import com.codecool.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {
    List<Product> cart;
    List<Float> prices;
    Menu menu;
    Scanner sc = new Scanner(System.in);
    Menu menu1 = new Menu("Plaza modelling", new String[]{"Create a plaza", "Exit"});
    Shop shop = null;
    AbstractPlaza plaza;
    ShopMenu shopMenu = new ShopMenu();

    public CmdProgram(String[] args){

    }

    public void run(){
        menu1.displayMenu();
        String input1 = sc.nextLine();
        switch (Integer.valueOf(input1)){
            case 1:
                System.out.println("Name your plaza: ");
                String input2 = sc.nextLine();
                System.out.println("What type of plaza you want to have? Rural, or City?");
                String typeOfPlaza = sc.nextLine();
                if(typeOfPlaza.equalsIgnoreCase("rural")){
                    plaza = new RuralPlaza(input2,new ArrayList<Shop>(),5);
                }else{
                    plaza = new CityPlaza(input2,new ArrayList<Shop>(), 5000);
                }
                Menu menu2 = new Menu(plaza.getName(), new String[]{"Check plaza status", "Open / Close plaza", "Enter a shop", "List shops", "Add a shop", "Remove a shop", "Exit"});
                while (true){
                    menu2.displayMenu();
                    System.out.println("What do you want to do next?");
                    String input3 = sc.nextLine();
                    switch (Integer.valueOf(input3)){
                        case 1:
                            if(plaza.isOpen()){
                                System.out.println("Plaza is open");
                            }else{
                                System.out.println("Plaza is closed");
                            }break;
                        case 2:
                            if(plaza.isOpen()){
                                plaza.close();
                                System.out.println("Plaza is closed.");
                            }else{
                                plaza.open();
                                System.out.println("Plaza is opened.");
                            }break;
                        case 3:
                            System.out.println("Enter name:");
                            String input4 = sc.nextLine();
                            try{
                                menuList(plaza.findShopByName(input4));
                            }catch (NoSuchShopException | PlazaIsClosedException e){
                                System.out.println(e);
                            }break;
                        case 4:
                            listShops(plaza);
                            break;
                        case 5:
                            String revenueOfPlaza;
                            System.out.println("Give it a name:");
                            String input5 = sc.nextLine();
                            System.out.println("Give it an owner");
                            String input6 = sc.nextLine();
                            if(plaza instanceof CityPlaza){
                                System.out.println("What's the yearly revenue of the plaza?");
                                revenueOfPlaza = sc.nextLine();
                            }else{
                                revenueOfPlaza = "0";
                            }
                            try{
                                plaza.addShop(new ShopImpl(input5, input6, Integer.valueOf(revenueOfPlaza)));
                            }catch(ShopAlreadyExistsException |PlazaIsClosedException | YearlyRevenueIsTooLowException | TooMuchShopsException e){
                                System.out.println(e);
                            }break;
                        case 6:
                            System.out.println("Give the shop's name:");
                            String input7 = sc.nextLine();
                            try{
                                shop = plaza.findShopByName(input7);
                                plaza.removeShop(shop);
                            }catch (NoSuchShopException | PlazaIsClosedException e){
                                System.out.println(e);
                            }break;
                        case 7:
                            System.out.println("Bye!");
                            System.exit(-1);
                            break;

                    }
                }

            case 2:
                System.out.println("Bye!");
                System.exit(-1);
                break;
        }
    }

    public void listShops(AbstractPlaza plaza){
        if(plaza.isOpen()){
            try{
                if(plaza.getShops().isEmpty()){
                    System.out.println("Plaza is empty.");
                }else{
                    for (Shop shop: plaza.getShops()){
                        System.out.println(shop.toString());
                    }
                }
            }catch (PlazaIsClosedException e){
                System.out.println(e);
            }
        }
    }

    public void listProducts(List<Product> products){
        if(products.isEmpty()){
            System.out.println("The shop is empty.");
        }else{
            for (Product product : products){
                if (product instanceof ClothingProduct){
                    System.out.println(product.toString());
                }else if(product instanceof FoodProduct && ((FoodProduct) product).isStillConsumable()){
                    System.out.println(product.toString());
                }
            }
        }

    }

    public void menuList(Shop shop){
        while(true){
            Menu shopMenu = new Menu(shop.getName(), new String[]{"Check Shop Status", "Open / Close Shop", "List products", "Find a product by name", "Buy product", "Add new product", "Add to an existing product", "Exit"});
            shopMenu.displayMenu();
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
                        System.out.println("Product added: " + product.toString());
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
