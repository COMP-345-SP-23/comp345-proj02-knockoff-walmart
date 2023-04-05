package edu.ithaca.barr.bank;
import java.util.*;

public class GroceryStoreUI {

    // Login after
    public static void main(String[] args){
        // should we use temporary users?
        // temporary logins


        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Grocery Store System.");
//        System.out.println("Temporary users available:\nManager: id = 0\t Username = 'manager'\nEmployee: id = 1\t Username = 'employee'");

        System.out.println("\nEnter which interface you'd like to use.");
        System.out.println("[1] Manager\n[2] Employee");

        int ans = scanner.nextInt();

        while(ans != 1 && ans != 2){
            System.out.println("Error! Please enter valid input.");
            ans = scanner.nextInt();
        }

        // TODO finish these up
        switch(ans){
            case 1 -> new ManagerUI();
            case 2 -> new EmployeeUI();
        }

//
//        Employee e = login(id, u);
//        assert e != null;
//        System.out.println(e.getClass().getSuperclass().equals(Employee.class));






    }

    public static boolean auth(Employee e){
        return GroceryStore.getEmployees().stream().anyMatch(employee ->
                employee.getId() == e.getId() || Objects.equals(employee.getName(), e.getName()));

    }

    public static Employee login(){
        Scanner scanner = new Scanner(System.in);
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
        }
        scanner.close();
        return e;
    }


    public static void register(){

        int id;
        String name;
        Scanner s = new Scanner(System.in);

        // ask between Manager & Employee
        System.out.print("Enter id: ");
        id = s.nextInt();
        System.out.print("Enter username: ");
        name = s.next();
        Employee e = new Employee(id, name);
        while(auth(e)){
            System.out.println("Error! Credentials in system. Please use a different id and username");
            System.out.print("Enter id: ");
            id = s.nextInt();
            System.out.print("Enter username: ");
            name = s.next();
            e = new Employee(id, name);

        }
        GroceryStore.getEmployees().add(e);
        s.close();


    }




}
