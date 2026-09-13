package main.java.com.store.model;

/*
Product reprezintă un articol disponibil spre vânzare
în magazin, cu atribute precum numele, prețul,
stocul și categoria. Clasa oferă metode pentru
actualizarea stocului și afișarea informațiilor despre produs
 */

import main.java.com.store.exception.InvalidQuantityException;
import main.java.com.store.exception.OutOfStockException;

public class Product {
    private static int nextId = 1;
    private final int id;
    private final String name;
    private final double price;
    private int stock;
    private final Category category;

    public Product(String name,
                   double price,
                   int stock,
                   Category category){
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0");
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.id = nextId++;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }

    public void decreaseStock(int quantity) throws OutOfStockException{
        if (quantity > stock){
            throw new OutOfStockException(name,stock,quantity);
        }
        stock -= quantity;
    }

    public void increaseStock(int quantity) throws InvalidQuantityException{
        if (quantity <= 0){
            throw new InvalidQuantityException(quantity);
        }
        stock += quantity;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name +
                           ", Price: " + price + ", Stock: " + stock +
                           ", Category: " + category);
    }
}
