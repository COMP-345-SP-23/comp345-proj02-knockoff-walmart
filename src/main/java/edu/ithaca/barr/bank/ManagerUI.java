package edu.ithaca.barr.bank;

import java.util.Scanner;

public class ManagerUI extends GroceryStoreUI{

    public ManagerUI(){
        Manager m = new Manager(0, "manager");
        GroceryStore.getEmployees().add(m);

        Scanner s = new Scanner(System.in);
        System.out.println("Manager interface");
        System.out.println("Temporary Manager available.\nId: 0\tUsername: manager");

        int ans;
        System.out.println("[1] Login\n[2] Register");
        ans = Integer.parseInt(s.nextLine());
        while(ans != 1 && ans!= 2){
            System.out.println("Invalid response.");
            ans = Integer.parseInt(s.nextLine());

        }

        switch(ans){
            case 1 -> login();
            case 2 -> register();
        }

    }






}
