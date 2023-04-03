package edu.ithaca.barr.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Apple", 1, "01/01/2022", 0.99, 123);
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
        assertEquals(1, product.getLocation());
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
}

