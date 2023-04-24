package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GSTest {
    
    @Test
    public void getAllProductsStringTest() throws Exception{
	    assertThrows(Exception.class, () -> GroceryStore.getAllProductsString());

	    Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/23", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);
        assertEquals("\n3245: Lettuce\n3246: Carrots", GroceryStore.getAllProductsString());
    }

    @Test
    public void productInventoryAllTest() throws Exception{
	    assertThrows(Exception.class, () -> GroceryStore.productInventoryAll());

	    Product product1 = new Product("Lettuce", "1", "04/05/2023", .79, 3245, false);
        GroceryStore.getProducts().add(product1);
        Product product2 = new Product("Carrots", "1", "04/05/2023", 2.49, 3246, false);
        GroceryStore.getProducts().add(product2);
	    assertEquals("\n3245 - Lettuce: Stock: 10, Expiration Date: 2023-04-05\n3246 - Carrots: Stock: 10, Expiration Date: 2023-04-05", GroceryStore.productInventoryAll());
    }







}
