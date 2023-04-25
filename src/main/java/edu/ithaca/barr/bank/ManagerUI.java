package edu.ithaca.barr.bank;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Manager UI
 * @author Liz Richards
 * @methods options, alertLowStock, addProduct, removeProduct, orderMoreProduct, productLowStock, productExpiration
 * @date
 */

public class ManagerUI extends GroceryStoreUI{
    List<Integer> options = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8);
    public static Scanner scanner = new Scanner(System.in);
    private static  Manager manager;

    // change aisle to A#, W#, String
    // W stands for Wall
    // Add options for special product type (Dairy, Meat, Frozen, Produce, Organic, Other) to team


    public ManagerUI(){
        // starter things
        manager= new Manager(0, "manager");

        GroceryStore.getEmployees().add(manager);

        System.out.println("Manager interface");
        System.out.println("Temporary Manager available.\nId: 0\tUsername: manager");

        new AuthSession("manager");

//        manager.alertProductExpiration();

        options();

    }

    public void options(){
        System.out.println("""
                \nManager Options:
                [1] Add New Product to Grocery Store
                [2] Remove a Product from the Grocery Store
                [3] Order More of a Product to the Store
                [4] Get Product Info\s
                [5] Stock of a Product
                [6] Expiration Date of a Product
                [7] Expiration for All Products
                [8] List items low on stock
                [9] View Store Inventory
                Enter 0 to end session.""");

        System.out.println("Please enter number associated with choice.");
        int opt;
        System.out.print("Enter response:");
        opt = Integer.parseInt(scanner.next());
        while(!options.contains(opt)){
            System.out.println("Error! Invalid response.");
            System.out.print("Enter response:");
            opt = Integer.parseInt(scanner.next());
        }

        switch(opt) {
            case 1 -> addProduct();
            case 2 -> removeProduct();
            case 3 -> orderMoreProduct();
            case 4 -> itemInfo();
            case 5 -> productLowStock();
            case 6 -> productExpirationDate();
            case 7 -> allProductsExpiration();
            case 8 -> alertLowStock();
            case 9 -> {
                storeInventory();
                options();
            }
        }
        System.out.println("Thank you for using Grocery Store!");
    }

    public void alertLowStock(){
        int threshold;
        System.out.print("\nPlease enter the minimum value for low stock:");
        threshold = Integer.parseInt(scanner.next());
        if(!(threshold >= 30 && threshold <= 180)){
            System.err.println("Error! Please enter a value between 30 and 180.");
            System.out.print("Desired threshold:");
            threshold = Integer.parseInt(scanner.next());
        }

        manager.alertLowStock(threshold);
    }

    public void addProduct(){
        String name, date, location;
        boolean org;
        int id;
        double price;
        System.out.println("\nAdd a Brand New Product to the Grocery Store!");


        System.out.print("Enter Product Name: ");
        name = scanner.next();
        System.out.print("Enter Product Location: ");
        location = (scanner.next());
        System.out.print("Enter Product Expiration Date (MM/DD/YYYY): ");
        date = scanner.next();
        System.out.print("Enter Product Price: ");
        price = Double.parseDouble(scanner.next());
        System.out.print("Enter Product Id: ");
        id = scanner.nextInt();
        System.out.println("Is Product Organic? 'true' or 'false'?");
        org = scanner.nextBoolean();

        Product p = new Product(name, location, date, price, id, org);

        while(Manager.getProductById(id) != null){
            System.err.println("Error! Product already in store.");
            System.out.println("Please Reenter Product Information");
            System.out.print("Enter Product Name: ");
            name = scanner.next();
            System.out.print("Enter Product Location: ");
            location = (scanner.next());
            System.out.print("Enter Product Expiration Date (MM/DD/YYYY): ");
            date = scanner.next();
            System.out.print("Enter Product Price: ");
            price = Double.parseDouble(scanner.next());
            System.out.print("Enter Product Id: ");
            id = scanner.nextInt();
            System.out.println("Is Product Organic? 'true' or 'false'?");
            org = scanner.nextBoolean();
        }

        p = new Product(name, location, date, price, id, org);

        manager.addProduct(p);
        System.out.println("Added " + name + " to the store!");


        options();

    }

    public void removeProduct(){
        String name;
        int id;
        System.out.println("\nRemove a Product from the Grocery Store Inventory.");
        System.out.println("Here's all the Products in the Grocery Store");
        storeInventory();
        System.out.println();

        System.out.print("Enter id:");
        id = scanner.nextInt();

        int count = 0;
        while(Manager.getProductById(id) == null){
            System.err.println("Error! Product not in store.");
            System.out.print("Enter id:");
            id = scanner.nextInt();
        }

        manager.removeProduct(Manager.getProductById(id));
        System.out.println("Removed product from Grocery Store");

        options();



    }
    public void orderMoreProduct(){
        int id, amount;
        System.out.println("\nOrder more of a particular Product!");
        storeInventory();
        System.out.print("Enter Product Id:");
        id = Integer.parseInt(scanner.next());
        System.out.print("Enter Amount to Add:");
        amount = Integer.parseInt(scanner.next());

        manager.orderMoreProduct(id, amount);
        System.out.println("Added " + amount + " to inventory");

        options();

    }
    public void productLowStock(){
        int id;
        System.out.println("\nDetermine if a Product is running low on stock!");
        System.out.println("Here's all Products in the store");
        storeInventory();
        System.out.print("Enter Product id:");
        id = Integer.parseInt(scanner.next());
        System.out.println(manager.alertLowStock(id));

        options();

    }

    public void allProductsExpiration(){
        int daysOut;
        System.out.println("Determine which products are nearing their expiration date!");

        System.out.print("Enter number of days out: ");
        daysOut = scanner.nextInt();

        manager.alertProductExpirationAllProducts(daysOut);

    }

    public void productExpirationDate(){
        int id, daysOut;
        System.out.println("\nDetermine if a Product is nearing their expiration date!");
        System.out.println("Here's all Products in the store");
        storeInventoryId();

        System.out.print("Enter Product id:");
        id = Integer.parseInt(scanner.next());
        System.out.print("Enter number of days out: ");
        daysOut = scanner.nextInt();

        while(Employee.getProductById(id) == null){
            System.err.println("Invalid id! Product not present in the store!\nPlease enter Product id: ");
            id = scanner.nextInt();
        }
        System.out.println(manager.alertProductExpiration(id, daysOut));
    }







}
