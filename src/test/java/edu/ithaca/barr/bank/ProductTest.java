package edu.ithaca.barr.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author  Matthew Weil
 * @date 4/12/2023
 */
class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Apple", "1", "01/01/2022", 0.99, 123);
    }

    @Test
    void testGetName() {
        assertEquals("Apple", product.getName());
    }

    @Test
    void testGetInventory() {
        assertEquals(10, product.getInventory());
    }

    @Test
    void testGetLocation() {
        assertEquals("1", product.getLocation());
    }

    @Test
    void testGetDateAsLocalDate() {
        LocalDate expectedDate = LocalDate.of(2022, 1, 1);
        assertEquals(expectedDate, product.getDateAsLocalDate());
    }

    @Test
    void testGetPrice() {
        assertEquals(0.99, product.getPrice());
    }

    @Test
    void testGetId() {
        assertEquals(123, product.getId());
    }

    @Test
    void testGetLocationBySku() {
        // Create some sample products
        Product p1 = new Product("Product 1", "1b", "01/01/2023", 10.0, 1001);
        Product p2 = new Product("Product 2", "2a", "01/01/2023", 20.0, 1002);
        Product p3 = new Product("Product 3", "3a", "01/01/2023", 30.0, 1003);
        Product p4 = new Product("Product 4", "4b", "01/01/2023", 40.0, 1004);
        GroceryStore.getProducts().add(p1);
        GroceryStore.getProducts().add(p2);
        GroceryStore.getProducts().add(p3);
        GroceryStore.getProducts().add(p4);

        // Test valid SKU numbers
        assertEquals("1b", Product.getLocationBySku(1001));
        assertEquals("2a", Product.getLocationBySku(1002));
        assertEquals("3a", Product.getLocationBySku(1003));
        assertEquals("4b", Product.getLocationBySku(1004));

        // Test invalid SKU number
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.getLocationBySku(9999));
        assertEquals("No product with SKU number 9999 found.", exception.getMessage());
    }
}