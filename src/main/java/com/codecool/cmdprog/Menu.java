package com.codecool.cmdprog;

public class Menu {

    private String title;
    private String[] options;

    public Menu(String title, String[] options) {
        this.title = title;
        this.options = options;
    }

    public void displayMenu() {
        System.out.println(title + "\n\n");
        int i = 1;
        for (String option:options) {
            if (option.equals(options[options.length-1])) {
                System.out.println("(" + i + ") " + option + "\n\n");
                i++;
            } else {
                System.out.println("(" + i + ") " + option + "\n\n");
                i++;
            }
        }
    }
}
