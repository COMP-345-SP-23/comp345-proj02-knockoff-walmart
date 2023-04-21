package edu.ithaca.barr.bank;

/**
 * This class tests the functionality of the ProductAlertExpiration class.
 * @author Matthew Weil
 * @methods testGetProductById() testGetProductByIdNotFound() testAlertProductExpirationMultipleProducts() testAlertProductExpirationNotAlmostExpired() testAlertProductExpirationAlmostExpired()
 * @date 03/31/2023
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
import org.junit.jupiter.api.Test;
 
public class ProductAlertExpirationTest {

    /**
     * Tests the getProductById() method to ensure it returns the correct product based on ID.
     */
    @Test
    public void testGetProductById() {
        Product product1 = new Product("Lettuce", "1", "04/05/20233", .79, 3245);
        GroceryStore.getProducts().add(product1);

        // Test with an existing product ID
        Product product = Manager.getProductById(3245);
        assertNotNull(product);
        assertEquals("Lettuce", product.getName());
    
        // Test with a non-existent product ID
        product = Manager.getProductById(100);
        assertNull(product);
    }

    /**
    * Tests the getProductById() method when the product ID is not found in the GroceryStore.
    */
    @Test
    public void testGetProductByIdNotFound() {
        Product product1 = new Product("Milk", "1", "04/05/2023", .79, 3245);
        GroceryStore.getProducts().add(product1);
        Product notFound = Manager.getProductById(4);
        assertNull(notFound);
    } 

    /**
    * Tests the alertProductExpiration() method when there are multiple products with different expiration dates.
    */
    @Test
    public void testAlertProductExpirationMultipleProducts() {
        Product product1 = new Product("Milk", "1", "04/05/2023", .79, 3245);
        Product product2 = new Product("Eggs", "2", "04/10/2023", 1.99, 3246);
        Product product3 = new Product("Bread", "3", "04/25/2023", 2.49, 3247);
        GroceryStore.getProducts().add(product1);
        GroceryStore.getProducts().add(product2);
        GroceryStore.getProducts().add(product3);
        Manager manager = new Manager(1234, "John");
        manager.alertProductExpirationAllProducts(7);
    }

    /**
    * Tests the alertProductExpiration() method when the product expiration date is not within 7 days of the current date.
    */
    @Test
    public void testAlertProductExpirationNotAlmostExpired() {
        Product product1 = new Product("Lettuce", "1", "07/05/2023", .79, 3245);
        GroceryStore.getProducts().add(product1);
        Manager manager = new Manager(1234, "John");
        
        String alertMessage = manager.alertProductExpiration(3245, 7);
        assertTrue(alertMessage.equals("No products near expiration date"));
    }

    /**
    * Tests the alertProductExpiration() method when the product expiration date is within 7 days of the current date.
    */
    @Test
    public void testAlertProductExpirationAlmostExpired() {
        Product product1 = new Product("Lettuce", "1", "04/07/2023", .79, 3245); //NEED TO CHANGE TO A DATE WITHIN 7 DAY OF CURRENT DATE TO SEE TEST
        GroceryStore.getProducts().add(product1);
        Manager manager = new Manager(1234, "John");

        String alertMessage = manager.alertProductExpiration(3245, 7);
        assertTrue(alertMessage.equals("Product " + product1.getName() + " is almost expired!"));
    }
}
