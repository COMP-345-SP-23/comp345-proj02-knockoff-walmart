package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @classname CustomerTests
 * @author Arabella Fielder
 * @methods AF: all
 * @date 04/17/23
 */

public class CustomerTests {
    
    @Test
    public void addToCartTest() throws IllegalArgumentException{
        Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/23", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);

        Customer customer1 = new Customer(12345678, "4321", 20.00);
        assertEquals(0,customer1.getCart().size());
        customer1.addToCart("Lettuce");
        assertEquals(1, customer1.getCart().size());
        customer1.addToCart("Carrots");
        assertEquals(2, customer1.getCart().size());

        assertThrows(IllegalArgumentException.class, () -> customer1.addToCart("Shampoo")); //test with item not in store
    }

    @Test
    public void putBackItemTest() throws IllegalArgumentException{
        Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/23", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);
        Customer customer1 = new Customer(12345678, "4321", 20.00);
        customer1.addToCart("Lettuce");
        customer1.addToCart("Carrots");

        assertEquals(2, customer1.getCart().size());
        customer1.putBackItem("Carrots");
        assertEquals(1, customer1.getCart().size());
        customer1.putBackItem("Lettuce");
        assertEquals(0, customer1.getCart().size());

        assertThrows(IllegalArgumentException.class, () -> customer1.putBackItem("Shampoo"));//tests with product that doesnt exist in cart
    
    }

    @Test
    public void addToTransactionTest(){
        Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/23", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);
        Customer customer1 = new Customer(12345678, "4321", 20.00);
        customer1.addToCart("Lettuce");
        customer1.addToCart("Carrots");
        customer1.addToCart("Carrots");

        assertEquals(10, product1.getInventory());
        assertEquals("", customer1.getCheckoutReceipt());
        customer1.addToTransaction(product1);
        assertEquals(9, product1.getInventory());
        assertEquals("\nLettuce: 0.79", customer1.getCheckoutReceipt());
        customer1.addToTransaction(product2);
        assertEquals(9, product2.getInventory());
        assertEquals("\nLettuce: 0.79\nCarrots: 2.49", customer1.getCheckoutReceipt());
        customer1.addToTransaction(product2);
        assertEquals(8, product2.getInventory());
        assertEquals("\nLettuce: 0.79\nCarrots: 2.49\nCarrots: 2.49", customer1.getCheckoutReceipt());
    }

    @Test
    public void makePaymentWCardTest(){
        Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/23", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);
        Customer customer1 = new Customer(12345678, "4321", 20.00);
        customer1.addToCart("Lettuce");
        customer1.addToCart("Lettuce");
        customer1.addToCart("Carrots");
        assertEquals(3, customer1.getCart().size());
        customer1.addToTransaction(product1);
        customer1.addToTransaction(product1);
        customer1.addToTransaction(product2);

        assertEquals(4.07, customer1.getCheckoutTotal());
        assertTrue(customer1.makePayment("4321"));
        assertEquals(0, customer1.getCart().size());

        customer1.addToCart("Lettuce");
        customer1.addToCart("Lettuce");
        customer1.addToCart("Carrots");
        customer1.addToTransaction(product1);
        customer1.addToTransaction(product1);
        customer1.addToTransaction(product2);

        assertThrows(IllegalArgumentException.class, () -> customer1.makePayment("1234"));

    }

    @Test
    public void makePaymentWCash() {
        Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/23", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);
        Customer customer1 = new Customer(12345678, "4321", 20.00);
        customer1.addToCart("Lettuce");
        customer1.addToCart("Carrots");
        customer1.addToCart("Lettuce");
        assertEquals(3, customer1.getCart().size());
        customer1.addToTransaction(product1);
        customer1.addToTransaction(product1);
        customer1.addToTransaction(product2);

        assertEquals(4.07, customer1.getCheckoutTotal());
        //assertTrue(customer1.makePayment(4.07));
        assertEquals(0, customer1.getCart().size());
        //assertEquals(15.93, customer1.getCashOnHand());

    }

    @Test
    public void checkoutWCashTest(){
        Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/23", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);
        Customer customer1 = new Customer(12345678, "4321", 20.00);
        customer1.addToCart("Lettuce");
        customer1.addToCart("Carrots");
        customer1.addToCart("Carrots");

        assertEquals("\nLettuce: 0.79\nCarrots: 2.49\nCarrots: 2.49", customer1.checkout());
        //assertEquals(5.77, customer1.getCheckoutTotal());
        assertEquals(0, customer1.getCart().size());
        //assertEquals(14.23, customer1.getCashOnHand());
        assertEquals(9, product1.getInventory());
        assertEquals(8, product2.getInventory());
        
        Product product3 = new Product("Steak", "4B", "05/11/2023", 8.00, 8765, false);
        GroceryStore.getProducts().add(product3);
        Customer customer2 = new Customer(12345678, "4321", 20.00);
        customer2.addToCart("Lettuce");
        customer2.addToCart("Lettuce");
        customer2.addToCart("Carrots");
        customer2.addToCart("Carrots");
        customer2.addToCart("Carrots");
        customer2.addToCart("Steak");
        customer2.addToCart("Steak");

        customer2.checkout();
        //checks that not all items in cart are bought due to lack of money
        assertEquals(17.05, customer2.getCheckoutTotal());
        assertEquals(1, customer2.getCart().size());
        //assertEquals(2.95, customer2.getCashOnHand());
        assertEquals(7, product1.getInventory());
        assertEquals(5, product2.getInventory());
        assertEquals(9, product3.getInventory());
        assertEquals("\nLettuce: 0.79\nLettuce: 0.79\nCarrots: 2.49\nCarrots: 2.49\nCarrots: 2.49\nSteak: 8.0", customer2.getCheckoutReceipt());
    }

    @Test
    public void checkoutWCardTest(){

        //test with valid pin number
        Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/23", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);
        Product product3 = new Product("Steak", "4B", "05/11/2023", 8.00, 8765, false);
        GroceryStore.getProducts().add(product3);
        
        Customer customer2 = new Customer(12345678, "4321", 20.00);

        customer2.addToCart("Lettuce");
        customer2.addToCart("Lettuce");
        customer2.addToCart("Carrots");
        customer2.addToCart("Carrots");
        customer2.addToCart("Carrots");
        customer2.addToCart("Steak");
        customer2.addToCart("Steak");

        assertEquals("\nLettuce: 0.79\nLettuce: 0.79\nCarrots: 2.49\nCarrots: 2.49\nCarrots: 2.49\nSteak: 8.0\nSteak: 8.0", customer2.checkout("4321"));

        assertEquals(25.05, customer2.getCheckoutTotal());
        assertEquals(0, customer2.getCart().size());
        assertEquals(20, customer2.getCashOnHand());
        assertEquals(8, product1.getInventory());
        assertEquals(7, product2.getInventory());
        assertEquals(8, product3.getInventory());
        
        //test with invalid pin number
        Customer customer1 = new Customer(12345678, "4321", 20.00);
        customer1.addToCart("Lettuce");
        customer1.addToCart("Carrots");
        customer1.addToCart("Carrots");

        assertThrows(IllegalArgumentException.class, () -> customer1.checkout("1987"));
        
    }
}
