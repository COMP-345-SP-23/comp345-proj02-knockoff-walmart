package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @classname ProductAlertExpirationTest
 * @author Matthew Weil
 * @methods 
 * @date 03/31/2023
 */

 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.util.ArrayList;
 
 import org.junit.jupiter.api.Test;
 import static org.junit.jupiter.api.Assertions.*;
 
 public class ProductAlertExpirationTest {
 
     private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
 
     @Test
     void testAlertProductExpiration() {
         // Arrange
         Manager manager = new Manager(1, "John Doe");
         String date = LocalDate.now().plusDays(3).format(formatter);
         Product product = new Product("Test Product", 1, date, 10.0, 1);
         boolean[] alertSent = {false};
         manager.setAlertHandler((p) -> alertSent[0] = true);
 
         // Act
         manager.alertProductExpiration(1);
 
         // Assert
         assertTrue(alertSent[0], "Expected an alert to be sent for the almost expired product.");
     }
 
     @Test
     void testAlertProductExpirationNoAlert() {
         // Arrange
         Manager manager = new Manager(1, "John Doe");
         String date = LocalDate.now().plusDays(8).format(formatter);
         Product product = new Product("Test Product", 1, date, 10.0, 1);
         boolean[] alertSent = {false};
         manager.setAlertHandler((p) -> alertSent[0] = true);
 
         // Act
         manager.alertProductExpiration(1);
 
         // Assert
         assertFalse(alertSent[0], "Expected no alert to be sent for the product that is not almost expired.");
     }
 
     @Test
     void testAlertProductExpirationMultipleProducts() {
         // Arrange
         Manager manager = new Manager(1, "John Doe");
         String date1 = LocalDate.now().plusDays(3).format(formatter);
         String date2 = LocalDate.now().plusDays(5).format(formatter);
         String date3 = LocalDate.now().plusDays(9).format(formatter);
         Product product1 = new Product("Test Product 1", 1, date1, 10.0, 1);
         Product product2 = new Product("Test Product 2", 2, date2, 20.0, 2);
         Product product3 = new Product("Test Product 3", 3, date3, 30.0, 3);
         ArrayList<Product> allProducts = new ArrayList<Product>();
         allProducts.add(product1);
         allProducts.add(product2);
         allProducts.add(product3);
         boolean[] alertSent = {false, false, false};
         manager.setAlertHandler((p) -> {
             if (p.equals(product1)) {
                 alertSent[0] = true;
             } else if (p.equals(product2)) {
                 alertSent[1] = true;
             } else if (p.equals(product3)) {
                 alertSent[2] = true;
             }
         });
 
         // Act
         manager.alertProductExpiration();
 
         // Assert
         assertTrue(alertSent[0], "Expected an alert to be sent for the almost expired product 1.");
         assertTrue(alertSent[1], "Expected an alert to be sent for the almost expired product 2.");
         assertFalse(alertSent[2], "Expected no alert to be sent for the product that is not almost expired.");
     }
 }
 

        

