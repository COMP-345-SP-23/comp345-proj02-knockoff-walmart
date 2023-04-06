package edu.ithaca.barr.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @classname Product
 * @author Arabella Fielder
 * @methods AF: increaseInventory, decreaseInventory - MW: getDateAsLocalDate
 * @date 04/03/23
 */

public class Product {
    
    private String name;
    private int inventoryTotal = 10;
    private int location;
    private String date;
    private double price;
    private int id;

    public Product(String nameIn, int locationIn, String dateIn, double priceIn, int idIn){
        name = nameIn;
        location = locationIn;
        date = dateIn;
        price = priceIn;
        id = idIn;
    }

    /*
     * @post increments the product's inventory
     * @param incrementValue value to increase inventory by
     */
    public void increaseInventory(int incrementValue){
        inventoryTotal += incrementValue;
    }

    /*
     * @post decrements the product's inventory
     * @param decrementValue value to decrease inventory by
     */
    public void decreaseInventory(int decrementValue){
        inventoryTotal = inventoryTotal - decrementValue;
    }

    /*
     * @return product's name
     */
    public String getName(){
        return name;
    }

    /*
     * @return inventoryTotal of product
     */
    public int getInventory(){
        return inventoryTotal;
    }

    /*
     * @return location of product (by aisle)
     */
    public int getLocation(){
        return location;
    }

    /*
     * @return expiration date in form of a string
     */
    public LocalDate getDateAsLocalDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(date, formatter);
    }

    /*
     * @return price of product
     */
    public double getPrice(){
        return price;
    }

    /*
     * @return product's id
     */
    public int getId(){
        return id;
    }

    /*
     * @post changed price of chosen product
     * @param priceIn for updated product
     */
    public void setPrice(double priceIn){
        price = priceIn;
    }
}
