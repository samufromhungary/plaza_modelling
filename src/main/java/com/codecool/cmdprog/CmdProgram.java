package com.codecool.cmdprog;

import com.codecool.api.*;

import java.util.List;
import java.util.Scanner;

public class CmdProgram {
    List<Product> cart;
    List<Float> prices;
    Menu menu;
    Scanner sc = new Scanner(System.in);
    Menu menu1 = new Menu("Plaza modelling", new String[]{"Create a plaza", "Exit"});
    PlazaImpl plaza;
    Shop shop = null;
    ShopMenu shopMenu;

    public CmdProgram(String[] args){

    }

    public void run(){
        menu1.displayMenu();
        int input1 = sc.nextInt();
        switch (input1){
            case 1:
                String input2 = sc.nextLine();
                plaza = new PlazaImpl(input2);
                Menu menu2 = new Menu("\nPlaza simulation", new String[]{"Check plaza status", "Open / Close plaza", "Enter a shop", "List shops", "Add a shop", "Remove a shop", "Exit"});
                while (true){
                    menu2.displayMenu();
                    System.out.println("What do you want to do next?");
                    int input3 = sc.nextInt();
                    switch (input3){
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
                                shopMenu.menuList(plaza.findShopByName(input4));
                            }catch (NoSuchShopException | PlazaIsClosedException e){
                                System.out.println(e);
                            }break;
                        case 4:
                            shopMenu.list(plaza);
                            break;
                        case 5:
                            System.out.println("Give it a name:");
                            String input5 = sc.nextLine();
                            System.out.println("Give it an owner");
                            String input6 = sc.nextLine();
                            try{
                                plaza.addShop(new ShopImpl(input5, input6));
                            }catch(ShopAlreadyExistsException |PlazaIsClosedException e){
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
}
