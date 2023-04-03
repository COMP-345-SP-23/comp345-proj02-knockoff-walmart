package edu.ithaca.barr.bank;

import java.util.ArrayList;

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
     * @param p
     */
    public void addProduct(Product p){

    }



}
