package main.java.com.store.exception;

/*
ProductNotFoundException apare atunci când
produsul căutat nu există în sistem.
Mesajul ei indică ID-ul produsului inexistent,
pentru a ajuta la identificarea rapidă a problemei.
 */

public class ProductNotFoundException extends StoreException{
    public ProductNotFoundException(int productId){
        super("Product not found with id: " + productId);
    }
}
