package main.java.com.store.exception;

/*
InvalidQuantityException este aruncată când
cantitatea introdusă este zero sau negativă.
Ea ajută la validarea datelor pentru a preveni operațiuni invalide.
 */

public class InvalidQuantityException extends StoreException{
    public InvalidQuantityException(int quantity){
        super("Quantity must be positive: " + quantity);
    }
}
