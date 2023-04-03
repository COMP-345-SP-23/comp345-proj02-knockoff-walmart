package edu.ithaca.barr.bank;

import java.util.*;
import java.util.ArrayList;
import java.util.Objects;

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
     */
    public void addProduct(Product p){
        ArrayList<Product> gs = new ArrayList<>();
        for(Product pro : GroceryStore.getProducts()){
            if(p == pro){
                throw new IllegalArgumentException();
            }
            else if(pro.getId() == p.getId() && Objects.equals(pro.getName(), p.getName())){
                throw new IllegalArgumentException();
            }
            else{
                GroceryStore.getProducts().add(p);

            }
        }

//        if(!gs.contains(p)){
//            if(gs.stream().noneMatch(product
//                    -> product.getId() == p.getId() || Objects.equals(product.getName(), p.getName()))){
//                GroceryStore.getProducts().add(p);
//            }
//            else{
//                throw new IllegalArgumentException();
//
//            }
//        }else{
//            throw new IllegalArgumentException();
//        }


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



}
