package main.java.com.store.exception;

/*
EmptyCartException apare atunci când se încearcă
finalizarea unei comenzi cu un coș gol.
Mesajul ei arată clar că checkout-ul nu poate
fi realizat fără produse în coș.
 */

public class EmptyCartException extends StoreException{
    public EmptyCartException(){
        super("Cannot checkout with an empty cart");
    }
}
