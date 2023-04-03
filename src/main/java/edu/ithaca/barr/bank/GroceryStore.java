package edu.ithaca.barr.bank;

import java.util.ArrayList;

public abstract class GroceryStore {
    
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

}
