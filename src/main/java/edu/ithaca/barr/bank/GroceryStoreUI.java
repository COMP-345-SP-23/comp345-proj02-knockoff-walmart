package edu.ithaca.barr.bank;
import java.util.*;

/**
 * The central UI
 * @author Liz Richards
 * @methods main, storeInventoryID,storeInventory, itemInfo
 * @date
 */

public abstract class GroceryStoreUI {

    private static final Scanner scanner = new Scanner(System.in);
    // Login after
   public static void main(String[] args){
       GroceryStore.getProducts().add(new Product("Apples", "A12", "04/05/2023", 1.14, 0));
       GroceryStore.getProducts().add(new Product("Bananas", "A12", "04/05/2023", .66, 1));
       GroceryStore.getProducts().add(new Product("Oranges", "A12", "04/05/2023", 1.00, 2));
       GroceryStore.getProducts().add(new Product("Grapes", "A12", "04/05/2023", 1.24, 3));
       GroceryStore.getProducts().add(new Product("Blueberries", "A12", "04/05/2023", 2.99, 4));

       // should we use temporary users?
        // temporary logins

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Grocery Store System.");
//        System.out.println("Temporary users available:\nManager: id = 0\t Username = 'manager'\nEmployee: id = 1\t Username = 'employee'");

       System.out.println("\nEnter which interface you'd like to use.");
       System.out.println("Please enter the number associated with choice");
       System.out.println("[1] Manager\n[2] Employee\n[3] Customer");

        int ans = scanner.nextInt();

        while(ans < 1 || ans > 3){
            System.out.println("Error! Please enter valid input.");
            ans = scanner.nextInt();
        }

        // TODO finish these up
        switch(ans){
            case 1 -> new ManagerUI();
            case 2 -> new EmployeeUI();
            case 3 -> new CustomerUI();
        }

    }

    public void storeInventoryId(){
        System.out.println("Id\tProduct Name");
        GroceryStore.getProducts().forEach(p ->  System.out.println(p.getId() + "\t" + p.getName()));

    }

    public void storeInventory(){
        System.out.println("Id\tQty\tProduct Name\tLocation");
        GroceryStore.getProducts().forEach(p ->  System.out.println(p.getId() + "\t" + p.getInventory() +
                "\t" + p.getName()));

    }

    // new method for customer only


    public void itemInfo(){
        int id;
        System.out.println("\nGet Information about a specific product!");
        storeInventoryId();
        System.out.print("Please enter item id:");
        id = Integer.parseInt(scanner.next());
        while(Manager.getProductById(id) == null){
            System.err.println("Error! Please enter a valid product id!");
            System.out.print("Product id:");
            id = Integer.parseInt(scanner.next());

        }
        Product p = Manager.getProductById(id);

        System.out.println("Id\tQty\tProduct\tLocation\tPrice");
        assert p != null;
        System.out.println(p.getId() +"\t" + p.getInventory() + "\t" + p.getName() + "\t" + p.getLocation() + "\t\t" + p.getPrice());

    }







}
