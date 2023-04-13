package edu.ithaca.barr.bank;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ManagerUI extends GroceryStoreUI{
    List<Integer> options = List.of(0, 1, 2, 3, 4, 5, 6);
    public static Scanner scanner = new Scanner(System.in);
    private static  Manager manager;

    // change aisle to A#, W#, String
    // W stands for Wall
    // Add options for special product type (Dairy, Meat, Frozen, Produce, Organic, Other) to team


    public ManagerUI(){
        // starter things
        GroceryStore.getProducts().add(new Product("Apples", 12, "04/05/2023", 1.14, 0));
        GroceryStore.getProducts().add(new Product("Bananas", 12, "04/05/2023", .66, 1));
        GroceryStore.getProducts().add(new Product("Oranges", 12, "04/05/2023", 1.00, 2));
        GroceryStore.getProducts().add(new Product("Grapes", 12, "04/05/2023", 1.24, 3));
        GroceryStore.getProducts().add(new Product("Blueberries", 12, "04/05/2023", 2.99, 4));

        manager= new Manager(0, "manager");

        GroceryStore.getEmployees().add(manager);

        Scanner s = new Scanner(System.in);
        System.out.println("Manager interface");

        System.out.println(manager.alertLowStock());

        System.out.println("Temporary Manager available.\nId: 0\tUsername: manager");

        AuthSession as = new AuthSession("manager");


        options();


    }

    public void options(){
        System.out.println("""
                \nManager Options:
                [1] Add New Product to Grocery Store
                [2] Remove a Product from the Grocery Store
                [3] Order More of a Product to the Store
                [4] Inventory on a Product\s
                [5] Expiration Date of a Product
                [6] View Store Inventory
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
            case 4 -> productLowStock();
            case 5 -> productExpirationDate();
            case 6 -> {
                storeInventory();
                options();
            }
        }
    }

    public void addProduct(){
        String name, date;
        int location, id;
        double price;
        System.out.println("\nAdd a Brand New Product to the Grocery Store!");


        System.out.print("Enter Product Name: ");
        name = scanner.next();
        System.out.print("Enter Product Location: ");
        location = Integer.parseInt(scanner.next());
        System.out.print("Enter Product Expiration Date (MM/DD/YYYY): ");
        date = scanner.next();
        System.out.print("Enter Product Price: ");
        price = Double.parseDouble(scanner.next());
        System.out.print("Enter Product Id: ");
        id = scanner.nextInt();

        Product p = new Product(name, location, date, price, id);

        while(Manager.getProductById(id) != null){
            System.err.println("Error! Product already in store.");
            System.out.println("Please Reenter Product Information");
            System.out.print("Enter Product Name: ");
            name = scanner.next();
            System.out.print("Enter Product Location: ");
            location = Integer.parseInt(scanner.next());
            System.out.print("Enter Product Expiration Date (MM/DD/YYYY): ");
            date = scanner.next();
            System.out.print("Enter Product Price: ");
            price = Double.parseDouble(scanner.next());
            System.out.print("Enter Product Id: ");
            id = scanner.nextInt();
        }
        p = new Product(name, location, date, price, id);

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

    public void productExpirationDate(){
        int id;
        System.out.println("\nDetermine if a Product is nearing their expiration date!");
        System.out.println("Here's all Products in the store");
        storeInventory();

        System.out.print("Enter Product id:");
        id = Integer.parseInt(scanner.next());
        System.out.println(manager.alertProductExpiration(id));

        options();

    }


    public void storeInventory(){
        System.out.println("Id\tQty\tProduct Name\tLocation\tPrice");
        GroceryStore.getProducts().forEach(p ->  System.out.println(p.getId() + "\t" + p.getInventory() +
                "\t" + p.getName()));

    }

}