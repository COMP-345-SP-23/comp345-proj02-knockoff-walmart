package edu.ithaca.barr.bank;

import java.util.ArrayList;
import java.time.LocalDate;

public class Manager extends Employee{
    
    public Manager(int idIn, String nameIn){
        super(idIn, nameIn);
        
    }


    /**
     * @param productId id of product to check stock of
     * @return string clarifying if product is low on stock or not as well as how many of the product remaining if low
     */
    public String alertLowStock(int productId){
        ArrayList<Product> products = GroceryStore.getProducts();
        Product alertProduct = null;
        for (Product product : products){
            if (product.getId() == productId){
                alertProduct = product;
                break;
            }
        }
        if (alertProduct.getInventory() < 5){
            return "Product Inventory Running Low: " + alertProduct.getInventory() + " remaining";
        }
        else{
            return "Product is not running low on stock: " + alertProduct.getInventory() + " remaining";
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
        if (lowStock.size() >0){
            String lowStockString = null;
            for (Product product : lowStock){
                lowStockString = ", " + product.getName() + " (" + product.getId() + "): " + product.getInventory() + " remaining";
            }
            return lowStockString;
        }
        else{
            return "No products are running low on stock";
        }
    }

    /**
     * Allows manager to add a brand new product to the grocery store
     * @param name, location, date, price, id
     */
    public void addProduct(String name, int location, String date, double price, int id){




    }


    /**
     * 
     * 
     */
    public void orderMoreProduct(gfiardhfiurhf){




        
    }

    /**
     * Alert if a product with the given ID is almost expired
     * @param productId The ID of the product to check
     */
    public void alertProductExpiration(int productId) {
        // Retrieve product information by ID (assuming you have a database or API)
        Product product = getProductById(productId);
        if (product == null) {
            System.out.println("Product with ID " + productId + " not found.");
            return;
        }

        // Check if the product is almost expired
        LocalDate expirationDate = product.getDateAsLocalDate();
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromNow = today.plusDays(7);
        if (expirationDate.isBefore(sevenDaysFromNow) || expirationDate.isEqual(sevenDaysFromNow)) {
            // Send an alert (e.g., email, push notification)
            sendProductExpirationAlert(product);
        }
    }

    /**
     * Alert if any products are almost expired
     */
    public void alertProductExpiration() {
        // Retrieve all products (assuming you have a database or API)
        ArrayList<Product> allProducts = getAllProducts();

        // Check each product and send an alert if it's almost expired
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromNow = today.plusDays(7);
        for (Product product : allProducts) {
            LocalDate expirationDate = product.getDateAsLocalDate();
            if (expirationDate.isBefore(sevenDaysFromNow) || expirationDate.isEqual(sevenDaysFromNow)) {
                // Send an alert (e.g., email, push notification)
                sendProductExpirationAlert(product);
            }
        }
    }

    // Helper methods for interacting with the database or sending alerts
    private Product getProductById(int productId) {
        ArrayList<Product> products = GroceryStore.getProducts();
        // Code to retrieve product information by ID goes here
        for (Product product : products){
            if (product.getId() == productId){
                Product alertProduct = product;
                break;
            }
        }
    }

    private ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = GroceryStore.getProducts();
        return products;
    }

    private void sendProductExpirationAlert(Product product) {
        // Code to send an alert
        System.out.println("Product " + product.getName() + " is almost expired!");
    }
}
