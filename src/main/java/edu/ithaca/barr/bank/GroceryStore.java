package edu.ithaca.barr.bank;

import java.util.ArrayList;

/**
 * @classname GrocerYStore
 * @author Arabella Fielder
 * @methods AF: getters
 * @date 04/03/23
 */


public class GroceryStore {
    
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private static ArrayList<Product> products = new ArrayList<Product>();
    private static double totalSales = 0;

    /*
     * @return list of employees
     */
    public static ArrayList<Employee> getEmployees(){
        return employees;
    }

    /*
     * @return list of products
     */
    public static ArrayList<Product> getProducts(){
        return products;
    }

    /*
     * @return totalSales of the store
     */
    public static double getTotalSales(){
        return totalSales;
    }

    /*
    * @return list of all products and their id numbers
    */
    public static String getAllProductsString() throws Exception{
	    String returnString = "";
	    for (Product product : products){
		    returnString = returnString + "\n" + product.getId() + ": " + product.getName();
	    }
	    if (returnString == ""){
		    throw new Exception("No products in this store");
	    }
	    return returnString;
    }

    /*
    * @return list of all products, their id number, inventory(stock) count, and expiration date
    */
    public static String productInventoryAll() throws Exception{
	    String returnString = "";
	    for (Product product : products){
		    returnString = returnString + "\n" + product.getId() + " - " + product.getName() + ": Stock: " + product.getInventory() + ", Expiration Date: " + product.getDateAsLocalDate();
	    }
	    if (returnString == ""){
		    throw new Exception("No products in this store");
	    }
	    return returnString;
    }

}
