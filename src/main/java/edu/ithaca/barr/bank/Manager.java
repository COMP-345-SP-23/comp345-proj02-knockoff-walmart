package edu.ithaca.barr.bank;

import java.util.*;
import java.time.LocalDate;

public class Manager extends Employee{
    
    public Manager(int idIn, String nameIn){
        super(idIn, nameIn);
        
    }
    /**
     * @param productId id of product to check stock of
     * @return string clarifying if product is low on stock or not as well as how many of the product remaining if low
     * @throws Exception
     */
    public String alertLowStock(int productId) throws Exception{
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
            throw new Exception("Product is not in this store");
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
     * @throws IllegalArgumentException if Product credentials currently in store
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
     * Allows manager to remove all instances of a product in the Grocery Store
     * @param p
     * @throws NoSuchElementException if Product not in store
     */
    public void removeProduct(Product p){
        if(GroceryStore.getProducts().contains(p)){
            GroceryStore.getProducts().remove(p);
        }else{
            throw new NoSuchElementException("Product does not exist in Grocery Store!");
        }

    }


    /**
     * allows manager to order more of a particular product
     * @param id of product to order more of
     * @param amountToAdd adding this particular amount to the products inventoryr
     * @post that product's inventory count will be increased
     * @return string either saying you made the order or that that product isnt in the system
     */
    public void orderMoreProduct(int id, int amountToAdd) throws Exception{
        ArrayList<Product> products = GroceryStore.getProducts();
        Product productToOrder = null;
        for (Product product : products){
            if (product.getId() == id){
                productToOrder = product;
                break;
            }
        }
        if (productToOrder == null){
            throw new Exception("Product does not exist in this store");
        }
        else{
            productToOrder.increaseInventory(amountToAdd);
        }
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
        // Code to retrieve product information by ID goes here
        return null;
    }

    private ArrayList<Product> getAllProducts() {
        // Code to retrieve all products goes here
        return null;
    }

    private void sendProductExpirationAlert(Product product) {
        // Code to send an alert (e.g., email, push notification) goes here
        System.out.println("Product " + product.getName() + " is almost expired!");
    }
}
