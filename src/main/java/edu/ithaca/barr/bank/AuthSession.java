package edu.ithaca.barr.bank;

import java.util.Objects;
import java.util.Scanner;

/**
 * Login/Register component for ManagerUI and EmployeeUI
 * @author Liz Richards
 * @methods login, register
 * @date
 */

public class AuthSession {
    private static final Scanner scanner = new Scanner(System.in);
    private final String type;

    public AuthSession(String type){
        this.type = type;
        int ans;
        System.out.println("[1] Login\n[2] Register");
        ans = Integer.parseInt(scanner.next());
        while(ans != 1 && ans!= 2){
            System.out.println("Invalid response.");
            ans = Integer.parseInt(scanner.next());

        }
        switch(ans){
            case 1 -> login();
            case 2 -> register();
        }

    }
    public static boolean auth(Employee e){
        return GroceryStore.getEmployees().stream().anyMatch(employee ->
                employee.getId() == e.getId() || Objects.equals(employee.getName(), e.getName()));

    }

    public void login(){
        int i;
        String use;
        System.out.print("Id:");
        i = scanner.nextInt();
        System.out.print("Username:");
        use = scanner.next();
        Employee e = new Employee(i, use);


        // add registration part
        while(!auth(e)){
            System.out.println("Error! User not in system!");
            System.out.print("Id:");
            i = scanner.nextInt();
            System.out.print("Username:");
            use = scanner.next();
            e = new Manager(i, use);
        }
    }

    public void register(){
        int id;
        String name;

        // ask between Manager & Employee
        System.out.print("Enter id: ");
        id = scanner.nextInt();

        System.out.print("Enter username: ");
        name = scanner.next();
        Employee e = new Employee(id, name);

        while(auth(e)){
            System.out.println("Error! Credentials in system. Please use a different id and username");
            System.out.print("Enter id: ");
            id = scanner.nextInt();
            System.out.print("Enter username: ");
            name = scanner.next();
            e = new Manager(id, name);

        }
        GroceryStore.getEmployees().add(e);
        System.out.println("\nPlease login with your registered information:\n");
        login();


    }
}
