package main.java.com.store.exception;

/*
OutOfStockException este folosită atunci când
se solicită o cantitate mai mare decât stocul
disponibil. Excepția reține numele produsului,
stocul disponibil și cantitatea cerută.
 */

public class OutOfStockException extends StoreException{
    private final String productName;
    private final int available;
    private final int requested;

    public OutOfStockException(String productName,
                               int available,
                               int requested){
        super("Out of stock: " + productName + ". Available: " + available + ", " +
                "Requested: " + requested);
        this.productName = productName;
        this.available = available;
        this.requested = requested;
    }

    public int getShortFall(){
        return requested - available;
    }
}
