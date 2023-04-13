package edu.ithaca.barr.bank;
import java.util.*;

public abstract class GroceryStoreUI {
    // Login after
   public static void main(String[] args){
        // should we use temporary users?
        // temporary logins

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Grocery Store System.");
//        System.out.println("Temporary users available:\nManager: id = 0\t Username = 'manager'\nEmployee: id = 1\t Username = 'employee'");

       System.out.println("\nEnter which interface you'd like to use.");
       System.out.println("Please enter the number associated with choice");
       System.out.println("[1] Manager\n[2] Employee(In Development)");

        int ans = scanner.nextInt();

        while(ans != 1 && ans != 2){
            System.out.println("Error! Please enter valid input.");
            ans = scanner.nextInt();
        }

        // TODO finish these up
        switch(ans){
            case 1 -> new ManagerUI();
            case 2 -> new EmployeeUI();// in dev
//            case 3 -> new CustomerUI();
        }


    }

}
