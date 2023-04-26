package edu.ithaca.barr.bank;

import java.util.List;
import java.util.Scanner;

/**
 * Customer UI
 * @author Liz Richards
 * @methods
 * @date
 */
public class CustomerUI {
    //TODO: make interactive, current for testing
    private static Customer customer; //= new Customer(123456789, "1234", 40.00);
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Integer> options = List.of(0, 1, 2, 3, 4, 5, 6);

    public CustomerUI() {
        double cash  = 0;
        System.out.println("Welcome to the Grocery Store!\nWe have a variety of fresh products ready for you.");
        System.out.println("");
        storeProducts();

        System.out.println("\nPlease enter cash on hand:");
        cash = Double.parseDouble(scanner.next());

        customer = new Customer(123456789, "1234", cash);


        options();
    }

    public void options() {
        System.out.println("""
                \nCustomer Options:
                [1] Add Items to Cart
                [2] View Cart
                [3] Checkout
                [4] See Items in Store
                Enter 0 to end session.""");

        System.out.println("Please enter number associated with choice.");
        int opt;
        System.out.print("Enter response:");
        opt = Integer.parseInt(scanner.next());
        while (!options.contains(opt)) {
            System.out.println("Error! Invalid response.");
            System.out.print("Enter response:");
            opt = Integer.parseInt(scanner.next());
        }

        switch (opt) {
            case 1 -> addToCart();
            case 2 -> {
                viewCart();
//                options();
            }
            case 3 -> checkout();
            case 4 -> storeProducts();

        }
    }


    // TODO: make prettier
    public void storeProducts() {
        System.out.println("Product Name\t\tPrice\tLocation");
        GroceryStore.getProducts().forEach(p -> System.out.println(p.getName() + "\t" + p.getPrice() + "\t" +  p.getLocation()));

    }
    public void addToCart() {
        String product;
        System.out.println("\n");
        System.out.println("Here you can add items to your cart until you're ready to checkout!\nEnter 'quit' when done purchasing.");
        storeProducts();

        System.out.print("Enter product name to add to cart:");
        product = scanner.next();
//        customer.addToCart(product);
        while(!product.equalsIgnoreCase("quit")){
            try{
                customer.addToCart(product);
                System.out.print("Enter product name to add to cart:");
                product = scanner.next();
            }catch(Exception e){
                System.err.println("Product is not in this store!\nPlease enter a valid product:");
                product = scanner.next();

            }

        }
        options();

    }

    public void putBack() {
        String item;
        System.out.println("\nRemove any item from cart.\nEnter 'quit' when done removing");
        viewCartUtil();
        System.out.print("\nEnter item to remove: ");
        item = scanner.next();

        while(!item.equalsIgnoreCase("quit")){
            try{
                customer.putBackItem(item);
                System.out.print("Enter item to remove:");
                item = scanner.next();
            }catch(Exception e){
                System.err.println("Item not in cart!\nPlease enter a valid item:");
                item = scanner.next();

            }

        }
        options();


    }

    // add options back or make it better
    public void viewCart(){
        double total = 0;
        if(customer.cart.size() == 0){
            System.out.println("\nNo items in cart!");

        }
        else {
            System.out.println("\nCart Items:\nName\tPrice");
            customer.cart.forEach(p -> System.out.println(String.join("\n", p.getName() + "\t" + p.getPrice())));
            for (Product product : customer.cart) {
                total += product.getPrice();
            }
            System.out.println("Total: " + total);

        }
        options();

    }

    public void viewCartUtil(){
        double total = 0;
        if(customer.cart.size() == 0){
            System.out.println("\nNo items in cart!");

        }
        else {
            System.out.println("\nCart Items:\nName\tPrice");
            customer.cart.forEach(p -> System.out.println(String.join("\n", p.getName() + "\t" + p.getPrice())));
            for (Product product : customer.cart) {
                total += product.getPrice();
            }
            System.out.println("Total: " + total);

        }
    }

    public String verify(){
        String pin;
        System.out.println("\nEnter Card Pin:");
        pin = scanner.next();
        while(pin.length() > 4){
            System.err.println("Error! Please enter valid pin number:");
            pin = scanner.next();

        }
        customer.cardPin = pin;
        return pin;

    }

    public void checkout() {
        String ans;
        int res, res0;
        System.out.println("\nWelcome to Self-Checkout!\nHere's all the items in your cart:");
        viewCartUtil();

        System.out.println("Are you ready to checkout? Enter 'y' or 'n'");
        ans = scanner.next();

        // input check method?, used multiple times
        while(!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n")){
            System.err.println("Invalid response! Please enter valid input.");
            ans = scanner.nextLine();
        }

        if(ans.equalsIgnoreCase("n")){
            System.out.println("Cart Options:\n[1] Add More Items to Cart\n[2] Remove Items from Cart");
            res = Integer.parseInt(scanner.nextLine());
            while(res != 1 && res != 2){
                System.err.println("Invalid Response! Please enter valid input.");
                res = Integer.parseInt(scanner.nextLine());
            }
            if(res == 1){
                addToCart();
            }else{
                putBack();
            }
        }else{
            String receipt;
            int input;
            System.out.println();
            viewCartUtil();

            System.out.println("How are you paying today?\n[1] Cash\n[2] Debit"); // change to specified card type for project
            input = Integer.parseInt(scanner.next());
            while(input != 1 && input != 2){
                System.err.println("Invalid entry! Please enter a valid response:");
                input = Integer.parseInt(scanner.next());
            }

            if(input == 1){
                try{
                    receipt = customer.checkout();
                    System.out.println(receipt);
                }catch(Exception e){
                    System.err.println("No items in cart!");
                }

            }else{
                String pin;
                pin = verify();

                // TODO: error test
                while(true){
                    try{
                        receipt = customer.checkout(pin);
                        break;
                    }catch(Exception e){
                        System.err.println("Invalid Pin!\nEnter card pin:");
                        pin = scanner.next();

                    }
                }
                System.out.println(receipt);
            }
            System.out.println("Would you like to return to the store?\n[1] Yes\n[2] No");
            res0 = scanner.nextInt();
            while(res0 > 2 || res0 < 1){
                System.err.println("Error! Please enter a valid response:");
                res0 = scanner.nextInt();
            }

            if(res0 == 1){
                options();
            }else{
                System.out.println("Thank you for using the Grocery Store!\nHave a nice day!");
            }


        }


    }



}
