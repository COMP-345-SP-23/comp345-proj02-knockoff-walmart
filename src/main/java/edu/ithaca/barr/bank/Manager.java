package edu.ithaca.barr.bank;

import java.util.*;

/**
 * @classname Manager
 * @author Arabella Fielder, Liz Richards, Matt Weil
 * @methods AF: alertLowStock, alertLowStock, orderMoreProduct - LR: addProduct - MW: alertProductExpiration, alertProductExpiration, helper methods
 * @date 04/06/23
 */

public class Manager extends Employee{
    
    public Manager(int idIn, String nameIn){
        super(idIn, nameIn);
        
    }
    /**
     * @param productId id of product to check stock of
     * @param lowStockCompare stock value to check when product stock is below
     * @return string clarifying if product is low on stock or not as well as how many of the product remaining if low
     * @throws NoSuchElementException
     */
    public String alertLowStock(int productId, int lowStockCompare) throws NoSuchElementException{
        ArrayList<Product> products = GroceryStore.getProducts();
        Product alertProduct = null;
        for (Product product : products){
            if (product.getId() == productId){
                alertProduct = product;
                break;
            }
        }
        if (alertProduct != null){
            if (alertProduct.getInventory() < lowStockCompare){
                return "Product " + alertProduct.getId() + " in aisle " + alertProduct.getLocation() + ": Inventory Running Low: " + alertProduct.getInventory() + " remaining";
            }
            else{
                return "Product " + alertProduct.getId() + " is not running low on stock: " + alertProduct.getInventory() + " remaining";
            }
        }
        else{
            throw new NoSuchElementException("Product is not in this store");
        }
    }

    /**
     * @param lowStockCompare stock value to check when product stock is below
     * @return string of all products running low on stock as well as how many of the product remaining if low
     */
    public String alertLowStock(int lowStockCompare){
        ArrayList<Product> lowStock = new ArrayList<Product>();
        for (Product product : GroceryStore.getProducts()){
            if (product.getInventory() < lowStockCompare){
                lowStock.add(product);
            }
        }
        if (lowStock.size() > 0){
            String lowStockString = "";
            for (Product product : lowStock){
                lowStockString = lowStockString + ", " + product.getName() + " (" + product.getId() + ") in aisle " + product.getLocation() + ": " + product.getInventory() + " remaining";
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
}
