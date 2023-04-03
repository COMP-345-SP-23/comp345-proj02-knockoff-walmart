package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * @classname ProductAlertStockTest
 * @author Arabella Fielder
 * @methods alertLowStockOneTest(), alertLowStockTestAll()
 * @date 03/30/23
 */

public class ProductAlertStockTest {

    @Test
    public void alertLowStockOneTest() throws Exception{
        Manager manager = new Manager(1234, "John");
        Product product1 = new Product("Lettuce", 1, "4/5/23", .79, 3245);
        GroceryStore groceryStore = new GroceryStore();
        groceryStore.getProducts().add(product1);
        assertEquals(10, product1.getInventory()); //ensuring starting inventory is correct
        assertEquals("Product is not running low on stock: 10 remaining", manager.alertLowStock(3245)); //tests for when inventory isnt running low
        product1.decreaseInventory(6);
        assertEquals(4, product1.getInventory()); //ensures lesser product inventory
        assertEquals("Product Inventory Running Low: 4 remaining", manager.alertLowStock(3245));//tests to check alert sent out that inventory is low for greatest amount possible

        Product product2 = new Product("Carrots", 1, "4/5/23", 2.49, 3246);
        groceryStore.getProducts().add(product2);
        assertEquals(10, product2.getInventory()); //ensuring starting inventory is correct
        product2.decreaseInventory(10);
        assertEquals(0, product2.getInventory()); //ensures lesser product inventory
        assertEquals("Product Inventory Running Low: 0 remaining", manager.alertLowStock(3246));//tests to check alert sent out that inventory is low for lowest possible amount

        assertThrows(Exception.class, () -> manager.alertLowStock(1234));//tests with product that doesnt exist
    }

    @Test
    public void alertLowStockAllTest(){
        GroceryStore groceryStore = new GroceryStore();
        Manager manager = new Manager(1234, "John");
        Product product1 = new Product("Lettuce", 1, "4/5/23", .79, 3245);
        Product product2 = new Product("Carrots", 1, "4/5/23", 2.49, 3246);
        Product product3 = new Product("Bread", 2, "4/16/23", 3.49, 1243);
        groceryStore.getProducts().add(product1);
        groceryStore.getProducts().add(product2);
        groceryStore.getProducts().add(product3);

        assertEquals("No products are running low on stock", manager.alertLowStock());//tests for when in a list of many none are running low on stock

        product1.decreaseInventory(7);
        assertEquals(", Lettuce (3245): 3 remaining", manager.alertLowStock());//tests when one of multiple products are running low

        product2.decreaseInventory(10);
        assertEquals(", Lettuce (3245): 3 remaining, Carrots (3246): 0 remaining", manager.alertLowStock()); //tests when multiple of multiple products are running low

        product3.decreaseInventory(8);
        assertEquals(", Lettuce (3245): 3 remaining, Carrots (3246): 0 remaining, Bread (1243): 2 remaining", manager.alertLowStock()); //tests when all of multiple products are running low
    }



    @Test
    public void orderMoreProductTest() throws Exception{
        GroceryStore groceryStore = new GroceryStore();
        Manager manager = new Manager(1234, "John");
        Product product1 = new Product("Lettuce", 1, "4/5/23", .79, 3245);
        groceryStore.getProducts().add(product1);
        // ArrayList<Product> products = GroceryStore.getProducts();
        // products.add(product1);
        assertEquals(10, product1.getInventory());//checks that starting inventory is 10
        manager.orderMoreProduct(3245, 12);//actual step of ordering more of a product
        assertEquals(22, product1.getInventory());//checks that inventory was increased by particular amount

        assertThrows(Exception.class, () -> manager.orderMoreProduct(5678, 5));//tests with product that doesnt exist, cant order more of something that doesnt preexist in the store
    }

    @Test
    public void incrementDecrementInventoryTest(){
        Product product1 = new Product("Lettuce", 1, "4/5/23", .79, 3245);
        assertEquals(10, product1.getInventory());//checks that starting inventory is 10
        product1.increaseInventory(5);
        assertEquals(15, product1.getInventory());//checks when increased by 5
        product1.increaseInventory(0);
        assertEquals(15, product1.getInventory());//checks when increased by nothing
        product1.decreaseInventory(4);
        assertEquals(11, product1.getInventory());//checks for decrementing by 4
        product1.decreaseInventory(0);
        assertEquals(11, product1.getInventory());//checks for decrementing by nothing
    }
    

}
