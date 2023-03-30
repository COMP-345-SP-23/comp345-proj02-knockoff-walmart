package edu.ithaca.barr.bank;

public class Product {
    
    private String name;
    private int inventoryTotal = 10;
    private int location;
    private String date;
    private double price;
    private int id;

    public Product(String nameIn, int locationIn, String dateIn, double priceIn, int idIn){
        name = nameIn;
        location = locationIn;
        date = dateIn;
        price = priceIn;
        id = idIn;
    }



}
