package edu.ithaca.barr.bank;

import java.util.ArrayList;

public class Customer {
    

/**
 * @classname Customer
 * @author Arabella Fielder
 * @methods AF: constructor, getters, checkout(), checkout(cardPin), makePayment(), makePayment(cardPin), addToCard(product), putBackItem(product), addToTransaction()
 * @date 04/12/23
 */

ArrayList<Product> cart;
String checkoutReceipt;
int cardNum;
int cardPin;
double cashOnHand;
double checkoutTotal;

public Customer(int cardNumIn, int cardPinIn, double cashOnHandIn){
    cart = new ArrayList<Product>();
    checkoutReceipt = "";
    cardNum = cardNumIn;
    cardPin = cardPinIn;
    cashOnHand = cashOnHandIn;
    checkoutTotal = 0;
}


/*
 * customer is able to purchase their chosen items from the store
 * @post GroceryStore's totalSales increases
 * @post Products inventories will decrease
 * @throws IllegalArgument Exception when no items to checkout with
 * must check cash balance to purchase total before each item is added
*/
public void checkout(){

}

/*
 * @param cardPin pin to verify card use
 * @post GroceryStore's totalSales increases
 * @post Products inventories will decrease
 * @throws IllegalArgument Exception when no items to checkout with
 * @throws IllegalArgumentException if card pin invalid
 * must verify pin first
 */
public void checkout(int cardPin){

}

/*
 * checks that their card and pin are valid 
 * @throws IllegalArgumentException if card pin invalid
 * @param cardPin pin to verify card use
 */
public void makePayment(int cardPin){

}

/*
 * checks that customer has enough money
 * @throws InsufficientResourcesException if not enough cash on hand
 */
public void makePayment(){

}


/*
 * @post removes product from customer's cart
 * @param itemName of product to be removed from cart
 * @throws exception when item isn't already in cart
 */
public void putBackItem(String itemName){

}


/*
 * @post product is added to cutomer's cart
 * @param itemName of product to be added to cart
 * @throws exception when item isnt even in the store
 */
public void addToCart(String itemName){
    ArrayList<Product> products = GroceryStore.getProducts();
    Product productToAdd = null;
    for (Product product : products){
        if (product.getName() == itemName){
            productToAdd = product;
            break;
        }
    }
    if (productToAdd != null){
        cart.add(productToAdd);
    }
    else{
        throw new IllegalArgumentException("Product is not in this store");
    }
}

/*
 * @post adds item to receipt list
 * @post adds up checkoutTotal cost
 * @post decrease product inventory
 * @param product that will be added to the transaction
 */
public double addToTransaction(Product product){
    return -1;
}

/*
 * @return itemsToBuy
 */
public ArrayList<Product> getCart(){
    return cart;
}

/*
 * @return checkoutReceipt
 */
public String getCheckoutReceipt(){
    return checkoutReceipt;
}

/*
 * @return cardNum
 */
public int getCardNum(){
    return cardNum;
}

/*
 * @return cardPin
 */
public int getCardPin(){
    return cardPin;
}

/*
 * @return cashOnHand
 */
public double getCashOnHand(){
    return cashOnHand;
}

/*
 * @return checkoutTotal
 */
public double getCheckoutTotal(){
    return checkoutTotal;
}

}

