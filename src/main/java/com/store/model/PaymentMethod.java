package main.java.com.store.model;

/*
PaymentMethod enumeră metodele de plată
disponibile în magazin, cum ar fi cardul
sau plata la livrare. El face sistemul
mai flexibil și mai clar din punct de vedere al
procesării plăților.
 */
public enum PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    CASH_ON_DELIVERY
}
