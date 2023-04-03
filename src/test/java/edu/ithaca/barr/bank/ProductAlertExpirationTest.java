package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @classname ProductAlertExpirationTest
 * @author Matthew Weil
 * @methods 
 * @date 03/31/2023
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductAlertExpirationTest {

    private Manager manager;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        manager = new Manager(123, "John Doe");
        product1 = new Product("Product 1", LocalDate.now().plusDays(5), 10.0, 1);
        product2 = new Product("Product 2", LocalDate.now().plusDays(10), 20.0, 2);
        product3 = new Product("Product 3", LocalDate.now().plusDays(15), 30.0, 3);
    }

    @Test
    void testAlertProductExpirationWithExistingProductId() {
        // Arrange
        ArrayList<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        Manager mockManager = mock(Manager.class);
        when(mockManager.getAllProducts()).thenReturn(products);
        doCallRealMethod().when(mockManager).alertProductExpiration(anyInt());

        // Act
        mockManager.alertProductExpiration(1);

        // Assert
        verify(mockManager).sendProductExpirationAlert(product1);
        verify(mockManager, never()).sendProductExpirationAlert(product2);
        verify(mockManager, never()).sendProductExpirationAlert(product3);
    }

    @Test
    void testAlertProductExpirationWithNonExistingProductId() {
        // Arrange
        Manager mockManager = mock(Manager.class);
        when(mockManager.getProductById(4)).thenReturn(null);
        doCallRealMethod().when(mockManager).alertProductExpiration(anyInt());

        // Act
        mockManager.alertProductExpiration(4);

        // Assert
        verify(mockManager, never()).sendProductExpirationAlert(any(Product.class));
    }

    @Test
    void testAlertProductExpiration() {
        // Arrange
        ArrayList<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        Manager mockManager = mock(Manager.class);
        when(mockManager.getAllProducts()).thenReturn(products);
        doCallRealMethod().when(mockManager).alertProductExpiration();

        // Act
        mockManager.alertProductExpiration();

        // Assert
        verify(mockManager).sendProductExpirationAlert(product1);
        verify(mockManager).sendProductExpirationAlert(product2);
        verify(mockManager).sendProductExpirationAlert(product3);
    }

    @Test
    void testGetProductById() {
        // Arrange
        ArrayList<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        Manager mockManager = mock(Manager.class);
        when(mockManager.getAllProducts()).thenReturn(products);
        doCallRealMethod().when(mockManager).getProductById(anyInt());

        // Act
        Product result = mockManager.getProductById(2);

        // Assert
        assertEquals(product2, result);
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        ArrayList<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        Manager mockManager = mock(Manager.class);
        when(mockManager.getAllProducts()).thenReturn(products);
        doCallRealMethod().when(mockManager).getAllProducts();

        // Act
        ArrayList<Product> result = mockManager.getAllProducts();

        // Assert
        assertEquals(products, result);
    }

    @Test
    void testSendProductExpirationAlert() {
        // Arrange
        Product mockProduct = mock(Product.class);
        when(mockProduct.getName()).thenReturn("Test Product");
        Manager manager = new Manager(1, "John Doe");
    
        // Act
        manager.sendProductExpirationAlert(mockProduct);
    
        // Assert
        String expectedAlertMessage = "Product Test Product is almost expired!";
        assertEquals(expectedAlertMessage, outContent.toString().trim());
    }
}

        

