package main.java.com.store.exception;

/*
CustomerNotFoundException este aruncată
când un client cu ID-ul specificat nu este găsit.
Aceasta oferă un mesaj clar care conține ID-ul clientului lipsă.
 */

public class CustomerNotFoundException extends StoreException{
    public CustomerNotFoundException(int customerId){
        super("Customer not found with id: " + customerId);
    }
}
