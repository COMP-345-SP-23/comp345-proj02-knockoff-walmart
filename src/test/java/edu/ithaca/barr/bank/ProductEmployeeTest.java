package edu.ithaca.barr.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author  Matthew Weil
 * @date 4/12/2023
 */
class ProductEmployeeTest {

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

    @Test
    public void testGetInventoryInfo() {
        Employee e = new Employee(0, "Emma" );
        Product p1 = new Product("Banana", "1b", "01/01/2023", 1.69, 1001);
        GroceryStore.getProducts().add(p1);
        String expected = "Product: Banana - Location: 1b - Stock: 10 - Price: $1.69 - Expiration date: 01/01/2023 - ID: 1001";
        String actual = e.getProductInfo(1001);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetInventoryInfoWithMultipleProducts(){
        Employee e = new Employee(0, "Emma" );
        Product p1 = new Product("Banana", "1b", "01/01/2023", 1.69, 1001);
        Product p2 = new Product("Orange", "2a", "02/02/2023", 2.50, 1002);
        Product p3 = new Product("Cucumber", "3a", "03/03/2023", 2.12, 1003);
        Product p4 = new Product("Apple", "4b", "04/04/2023", 4.01, 1004);

        GroceryStore.getProducts().add(p1);
        GroceryStore.getProducts().add(p2);
        GroceryStore.getProducts().add(p3);
        GroceryStore.getProducts().add(p4);

        String expected1 = "Product: Banana - Location: 1b - Stock: 10 - Price: $1.69 - Expiration date: 01/01/2023 - ID: 1001";
        String actual1 = e.getProductInfo(1001);
        assertEquals(expected1, actual1);

        String expected2 = "Product: Orange - Location: 2a - Stock: 10 - Price: $2.50 - Expiration date: 02/02/2023 - ID: 1002";
        String actual2 = e.getProductInfo(1002);
        assertEquals(expected2, actual2);

        String expected3 = "Product: Cucumber - Location: 3a - Stock: 10 - Price: $2.12 - Expiration date: 03/03/2023 - ID: 1003";
        String actual3 = e.getProductInfo(1003);
        assertEquals(expected3, actual3);

        String expected4 = "Product: Apple - Location: 4b - Stock: 10 - Price: $4.01 - Expiration date: 04/04/2023 - ID: 1004";
        String actual4 = e.getProductInfo(1004);
        assertEquals(expected4, actual4);
    }
}