package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductAlertStockTest {
    

    @Test
    public void alertLowStockOneTest(){
        Manager manager = new Manager(1234, "John");
        GroceryStore groceryStore = new GroceryStore();
        Product product1 = new Product("Lettuce", 1, "4/5/23", .79, 3245);
        groceryStore.getProducts().add(product1);
        assertEquals(10, product1.getInventory()); //ensuring starting inventory is correct
        product1.decreaseInventory(6);
        assertEquals(4, product1.getInventory()); //ensures lesser product inventory
        assertEquals("Product Inventory Running Low: 4 remaining", manager.alertLowStock(3245));//tests to check alert sent out that inventory is low for greatest amount possible

        Product product2 = new Product("Carrots", 1, "4/5/23", 2.49, 3246);
        groceryStore.getProducts().add(product2);
        assertEquals(10, product2.getInventory()); //ensuring starting inventory is correct
        product1.decreaseInventory(10);
        assertEquals(0, product2.getInventory()); //ensures lesser product inventory
        assertEquals("Product Inventory Running Low: 0 remaining", manager.alertLowStock(3246));//tests to check alert sent out that inventory is low for lowest possible amount
    }

    @Test
    public void alertLowStockAllTest(){
        Manager manager = new Manager(1234, "John");
        GroceryStore groceryStore = new GroceryStore();
        Product product1 = new Product("Lettuce", 1, "4/5/23", .79, 3245);
        Product product2 = new Product("Carrots", 1, "4/5/23", 2.49, 3246);
        Product product3 = new Product("Bread", 2, "4/16/23", 3.49, 1243);
        groceryStore.getProducts().add(product1);
        groceryStore.getProducts().add(product2);
        groceryStore.getProducts().add(product3);

        product1.decreaseInventory(7);
        assertEquals("Lettuce (3245): 3 remaining", manager.alertLowStock());//tests when one of multiple products are running low

        product2.decreaseInventory(10);
        assertEquals("Lettuce (3245): 3 remaining, Carrots (3246): 0 remaining", manager.alertLowStock()); //tests when multiple of multiple products are running low

        product3.decreaseInventory(8);
        assertEquals("Lettuce (3245): 3 remaining, Carrots (3246): 0 remaining, Bread (1243): 2 remaining", manager.alertLowStock()); //tests when all of multiple products are running low
    }
   
}
