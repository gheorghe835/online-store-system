package main.java.com.store.service;

import main.java.com.store.exception.*;
import main.java.com.store.model.*;

import java.util.ArrayList;
import java.util.List;

/*
Store este clasa de service care gestionează produsele,
clienții și comenzile din sistem. Ea oferă metode pentru
adăugare, căutare, plasarea comenzilor și
afișarea informațiilor generale din magazin.
 */

public class Store {
    private final List<Product> products;
    private final List<Customer> customers;
    private final List<Order> orders;

    public Store(){
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public Product addProduct(String name,
                              double price,
                              int stock,
                              Category category){
        Product product = new Product(name,price,stock,category);
        products.add(product);
        return product;
    }

    public Customer addCustomer(String name,
                                String email){
        Customer customer = new Customer(name,email);
        customers.add(customer);
        return customer;
    }

    public Product findProductById(int id) throws ProductNotFoundException{
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Customer findCustomerById(int id) throws  CustomerNotFoundException{
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Order placeOrder(int customerId,
                            List<OrderItem> items) throws CustomerNotFoundException,
            EmptyCartException, OutOfStockException, InvalidQuantityException {
        Customer customer = findCustomerById(customerId);
        if (items.isEmpty()){
            throw new EmptyCartException();
        }
        Order order = new Order(customer);
        for (OrderItem item : items){
            order.addItem(item.product(), item.quantity());
        }
        orders.add(order);
        return order;
    }

    public void displayCatalog(){
        for (Product product : products){
            product.displayInfo();
        }
    }

    public void displayAllOrders(){
        for (Order order : orders){
            order.displayInfo();
        }
    }

    public void displayCustomers(){
        for (Customer customer : customers){
            customer.displayInfo();
        }
    }
}
