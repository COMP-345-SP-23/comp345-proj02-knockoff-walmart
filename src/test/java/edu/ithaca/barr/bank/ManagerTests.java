package edu.ithaca.barr.bank;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Elizabeth Richards
 * @date 4/2/2023
 */

public class ManagerTests {

    @Test
    void addProductTest(){
        Manager m = new Manager(0, "Manny" );
        Product p = new Product("product", 12, "03/31/2023", 10.00, 0);

        //successful add
        m.addProduct(p);
        assertTrue(GroceryStore.getProducts().size() > 0); //checks that store products is more than zero
        assertTrue(GroceryStore.getProducts().contains(p)); // checks that store contains added product

        //throws illegal argument exception if product already in store
        assertThrows(IllegalArgumentException.class, ()-> m.addProduct(p));

        Product p2 = new Product("product2", 12, "03/31/2023", 10.00, 0);
        //throws illegal argument exception if id is already in list
        assertThrows(IllegalArgumentException.class, ()-> m.addProduct(p2));

        Product p3 = new Product("product", 12, "03/31/2023", 10.00, 1);

        //throws illegal argument exception if name is already in list
        assertThrows(IllegalArgumentException.class, ()-> m.addProduct(p2));


    }
}
