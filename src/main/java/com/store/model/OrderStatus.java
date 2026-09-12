package main.java.com.store.model;

/*
OrderStatus reprezintă stările posibile ale
unei comenzi, de la PENDING până la DELIVERED
sau CANCELLED. Acest enum este folosit pentru a
urmări evoluția unei comenzi în sistem.
 */

public enum OrderStatus {
    PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
