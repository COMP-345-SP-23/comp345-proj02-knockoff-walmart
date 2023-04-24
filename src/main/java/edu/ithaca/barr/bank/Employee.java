package edu.ithaca.barr.bank;

import java.time.LocalDate;
import java.util.ArrayList;

public class Employee {
    
/**
 * @classname Employee
 * @author Arabella Fielder, Matt Weil
 * @methods AF: constructor, getters
 * @date 04/03/23
 */
    

    private int id;
    private String name;

    public Employee(int idIn, String nameIn){
        id = idIn;
        name = nameIn;
    }


    /*
     * @return employee's id
     */
    public int getId(){
        return id;
    }

    /*
     * @return employee's name
     */
    public String getName(){
        return name;
    }

    /**
     * Alert if a product with the given ID is almost expired
     * @param productId The ID of the product to check
     */
    public String alertProductExpiration(int productId, int daysOut) {
        // Retrieve product information by ID
        Product product = getProductById(productId);
        if (product == null) {
            return "Product with ID " + productId + " not found.";
        }

        // Check if the product is almost expired
        LocalDate expirationDate = product.getDateAsLocalDate();
        LocalDate today = LocalDate.now();
        LocalDate daysFromNow = today.plusDays(daysOut);
        if (expirationDate.isBefore(daysFromNow) || expirationDate.isEqual(daysFromNow)) {
            return "Product " + product.getName() + " is almost expired!";
        }else{
            return "No products near expiration date";
        }
    }

    /**
     * Alert if any products are almost expired
     */
    public void alertProductExpirationAllProducts(int daysOut) {
        // Retrieve all products (assuming you have a database or API)
        ArrayList<Product> allProducts = GroceryStore.getProducts();

        // Check each product and send an alert if it's almost expired
        LocalDate today = LocalDate.now();
        LocalDate daysFromNow = today.plusDays(daysOut);
        for (Product product : allProducts) {
            LocalDate expirationDate = product.getDateAsLocalDate();
            if (expirationDate.isBefore(daysFromNow) || expirationDate.isEqual(daysFromNow)) {
                System.out.println("Product " + product.getName() + " is almost expired!");
            }
        }
    }

    // Helper methods for interacting with the database or sending alerts
    static Product getProductById(int productId) {
        ArrayList<Product> products = GroceryStore.getProducts();
        // Code to retrieve product information by ID
        for (Product product : products){
            if (product.getId() == productId){
                return product; // Return the product if found
            }
        }
        return null;
    }

    //Get Product information and inventory
    public String getProductInfo(int productId){
        return "hi";
    }


}
