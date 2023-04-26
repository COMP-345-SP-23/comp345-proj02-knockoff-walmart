package edu.ithaca.barr.bank;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Customer {
    

/**
 * @classname Customer
 * @author Arabella Fielder
 * @methods AF: constructor, getters, checkout(), checkout(cardPin), makePayment(), makePayment(cardPin), addToCard(product), putBackItem(product), addToTransaction()
 * @date 04/17/23
 */

List<Product> cart;
String checkoutReceipt;
int cardNum;
String cardPin;
double cashOnHand;
double checkoutTotal;

public Customer(int cardNumIn, String cardPinIn, double cashOnHandIn){
    cart = new CopyOnWriteArrayList<Product>();
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
public String checkout(){
    boolean each = false;
    for (Product product : cart){
        each = makePayment(product.getPrice());
        if (each){
            addToTransaction(product);
            cashOnHand = cashOnHand - product.getPrice();
        }
    }
    return checkoutReceipt;
}

/*
 * @param cardPin pin to verify card use
 * @post GroceryStore's totalSales increases
 * @post Products inventories will decrease
 * @throws IllegalArgument Exception when no items to checkout with
 * @throws IllegalArgumentException if card pin invalid
 * must verify pin first
 */
public String checkout(String cardPinEntered){
    if (!cardPinEntered.equals(cardPin)){
        throw new IllegalArgumentException("Invalid Pin");
    }
    else{
        for (Product product : cart){
            addToTransaction(product);
        }
    }
    return checkoutReceipt;
}

/*
 * checks that their card and pin are valid 
 * @throws IllegalArgumentException if card pin invalid
 * @param cardPin pin to verify card use
 * @return true when valid card pin
 */
public boolean makePayment(String cardPinEntered) throws IllegalArgumentException{
    if (!cardPinEntered.equals(cardPin)){
        throw new IllegalArgumentException("Invalid Pin");
    }
    return true;
}

/*
 * checks that customer has enough money
 * @param toPay amount to pay for groceries
 * @throws InsufficientResourcesException if not enough cash on hand
 */
public boolean makePayment(double toPay){
    if (cashOnHand - toPay >= 0){
        return true;
    }
    return false;
}


/*
 * @post removes product from customer's cart
 * @param itemName of product to be removed from cart
 * @throws exception when item isn't already in cart
 */
public void putBackItem(String itemName){
    Product productToRemove = null;
    for (Product product : cart){
        if (product.getName().equalsIgnoreCase(itemName)){
            productToRemove = product;
            break;
        }
    }
    if (productToRemove != null){
        cart.remove(productToRemove);
    }
    else{
        throw new IllegalArgumentException("Product is not in your cart");
    }
}


/*
 * @post product is added to cutomer's cart
 * @param itemName of product to be added to cart
 * @throws exception when item isnt even in the store
 */
public void addToCart(String itemName){
    List<Product> products = GroceryStore.getProducts();
    Product productToAdd = null;
    for (Product product : products){
        if (product.getName().equalsIgnoreCase(itemName)){
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
public void addToTransaction(Product product){
        checkoutReceipt = checkoutReceipt + "\n" + product.getName() + ": " + product.getPrice();
        product.decreaseInventory(1);
        checkoutTotal += product.getPrice();
        putBackItem(product.getName());
}

/*
 * @return itemsToBuy
 */
public List<Product> getCart(){
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
public String getCardPin(){
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

