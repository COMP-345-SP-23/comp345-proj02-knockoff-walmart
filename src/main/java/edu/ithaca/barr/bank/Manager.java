package edu.ithaca.barr.bank;

import java.util.*;
import java.time.LocalDate;


/**
 * @classname Manager
 * @author Arabella Fielder, Liz Richards, Matt Weil
 * @methods AF: alertLowStock, alertLowStock, orderMoreProduct - LR: addProduct - MW: alertProductExpiration, alertProductExpiration, helper methods
 * @date 04/03/23
 */

public class Manager extends Employee{
    
    public Manager(int idIn, String nameIn){
        super(idIn, nameIn);
        
    }
    /**
     * @param productId id of product to check stock of
     * @return string clarifying if product is low on stock or not as well as how many of the product remaining if low
     * @throws NoSuchElementException
     */
    public String alertLowStock(int productId) throws NoSuchElementException{
        ArrayList<Product> products = GroceryStore.getProducts();
        Product alertProduct = null;
        for (Product product : products){
            if (product.getId() == productId){
                alertProduct = product;
                break;
            }
        }
        if (alertProduct != null){
            if (alertProduct.getInventory() < 5){
                return "Product Inventory Running Low: " + alertProduct.getInventory() + " remaining";
            }
            else{
                return "Product is not running low on stock: " + alertProduct.getInventory() + " remaining";
            }
        }
        else{
            throw new NoSuchElementException("Product is not in this store");
        }
    }

    /**
     * @return string of all products running low on stock as well as how many of the product remaining if low
     */
    public String alertLowStock(){
        ArrayList<Product> lowStock = new ArrayList<Product>();
        for (Product product : GroceryStore.getProducts()){
            if (product.getInventory() < 5){
                lowStock.add(product);
            }
        }
        if (lowStock.size() > 0){
            String lowStockString = "";
            for (Product product : lowStock){
                lowStockString = lowStockString + ", " + product.getName() + " (" + product.getId() + "): " + product.getInventory() + " remaining";
            }
            return lowStockString;
        }
        else{
            return "No products are running low on stock";
        }
    }

    /**
     * Allows manager to add a brand new product to the grocery store
     * @param p
     */
    public void addProduct(Product p){

        if(!GroceryStore.getProducts().contains(p)){
            if(GroceryStore.getProducts().stream().noneMatch(product
                    -> product.getId() == p.getId() || Objects.equals(product.getName(), p.getName()))){
                GroceryStore.getProducts().add(p);
            }
            else{
                throw new IllegalArgumentException();

            }
        }else{
            throw new IllegalArgumentException();
        }


    }


    /**
     * allows manager to order more of a particular product
     * @param id of product to order more of
     * @param amountToAdd adding this particular amount to the products inventoryr
     * @post that product's inventory count will be increased
     * @return string either saying you made the order or that that product isnt in the system
     * @throws NoSuchElementException
     */
    public void orderMoreProduct(int id, int amountToAdd) throws NoSuchElementException{
        ArrayList<Product> products = GroceryStore.getProducts();
        Product productToOrder = null;
        for (Product product : products){
            if (product.getId() == id){
                productToOrder = product;
                break;
            }
        }
        if (productToOrder == null){
            throw new NoSuchElementException("Product does not exist in this store");
        }
        else{
            productToOrder.increaseInventory(amountToAdd);
        }
    }

    /**
     * Alert if a product with the given ID is almost expired
     * @param productId The ID of the product to check
     */
    public String alertProductExpiration(int productId) {
        // Retrieve product information by ID
        Product product = getProductById(productId);
        if (product == null) {
            return "Product with ID " + productId + " not found.";
        }

        // Check if the product is almost expired
        LocalDate expirationDate = product.getDateAsLocalDate();
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromNow = today.plusDays(7);
        if (expirationDate.isBefore(sevenDaysFromNow) || expirationDate.isEqual(sevenDaysFromNow)) {
            return "Product " + product.getName() + " is almost expired!";
        }else{
            return "No products near expiration date";
        }
    }

    /**
     * Alert if any products are almost expired
     */
    public void alertProductExpiration() {
        // Retrieve all products (assuming you have a database or API)
        ArrayList<Product> allProducts = GroceryStore.getProducts();

        // Check each product and send an alert if it's almost expired
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromNow = today.plusDays(7);
        for (Product product : allProducts) {
            LocalDate expirationDate = product.getDateAsLocalDate();
            if (expirationDate.isBefore(sevenDaysFromNow) || expirationDate.isEqual(sevenDaysFromNow)) {
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
}
