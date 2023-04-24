package edu.ithaca.barr.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @classname Product
 * @author Arabella Fielder, Matt Weil
 * @methods AF: increaseInventory, decreaseInventory - MW: getDateAsLocalDate
 * @date 04/03/23
 */

public class Product {
    
    private String name;
    private int inventoryTotal = 10;
    private String location;
    private String date;
    private double price;
    private int id;
    private boolean organic;

    public Product(String nameIn, String locationIn, String dateIn, double priceIn, int idIn, boolean organicIn){
        name = nameIn;
        organic = organicIn;
        location = locationIn;
        date = dateIn;
        if (organic){
            price = priceIn * Math.exp(1);
        }
        else{
            price = priceIn;
        }
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
    public String getLocation(){
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

    /**
     * Returns the location of the product with the given SKU number.
     * @param sku the SKU number of the product to look up
     * @return the location of the product with the given SKU number
     * @throws IllegalArgumentException if no product with the given SKU number is found
     */
    public static String getLocationBySku(int sku) throws IllegalArgumentException {
        ArrayList<Product> products = GroceryStore.getProducts();
        
        for (Product product : products){
            if (product.getId() == sku){
                return product.getLocation(); // Return the product if found
            }
        }
        // If no product with the given SKU number is found, throw an exception
        throw new IllegalArgumentException("No product with SKU number " + sku + " found.");
    }
}
