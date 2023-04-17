package edu.ithaca.barr.bank;

import java.util.Scanner;
//import java-text-utils;

/**
 * Customer UI
 * @author Liz Richards
 * @methods
 * @date
 */

public class CustomerUI{
    private static final Customer c = new Customer();
    private static final Scanner scanner = new Scanner(System.in);
    public CustomerUI(){
        System.out.println("Welcome to the Grocery Store!\nWe have a variety of fresh products ready for you.");
        storeProducts();

    }

    public void storeProducts(){
//        System.out.println("Product Name\t\tPrice\tLocation");
//        GroceryStore.getProducts().forEach(p ->  System.out.format("%6s%30f%16s\n", p.getName(), p.getPrice(), p.getLocation()));

    }
}
