package edu.ithaca.barr.bank;

import java.util.List;
import java.util.Scanner;

/**
 * Employee UI
 * @author Liz Richards
 * @methods options, findProduct
 */


public class EmployeeUI extends GroceryStoreUI{
    List<Integer> options = List.of(0, 1, 2, 3, 4);
    private static final Scanner scanner = new Scanner(System.in);
    private static final Employee employee = new Employee(1, "employee");


    public EmployeeUI(){
        GroceryStore.getEmployees().add(employee);

        System.out.println("Employee interface");
        System.out.println("Temporary Employee available.\nId: 1\tUsername: employee");


        new AuthSession("employee");

        options();

        //alert product expiration

    }


    public void options(){
        System.out.println("""
                \nEmployee Options:
                [1] Find a Product by Id
                [2] Get Expired Products
                [3] Get Product info
                [4] View Store Inventory
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
            case 1 -> findProduct();
            case 2 -> {
//                productExpirationDate();
                options();
            }
            case 3 -> {
                itemInfo();
                options();
            }
            case 4 -> {
                storeInventory();
                options();
            }
        }
        System.out.println("Thank you for using Grocery Store!");
    }

    public void findProduct(){
        int id;
        System.out.println("\nFind product by id!");
        System.out.print("Please enter the id of the product:");
        id = Integer.parseInt(scanner.next());

        while(Manager.getProductById(id) == null){
            System.err.print("Error! Please enter a valid product id\n");
            id = Integer.parseInt(scanner.next());
        }

        Product p = Manager.getProductById(id);
        System.out.println("Id\tProduct\tLocation");
        assert p != null;
        System.out.println(p.getId() + "\t" + p.getName() + "\t" + p.getLocation());

        options();


    }




}


