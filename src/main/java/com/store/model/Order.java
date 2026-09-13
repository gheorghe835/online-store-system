package main.java.com.store.model;

import main.java.com.store.exception.InvalidQuantityException;
import main.java.com.store.exception.OutOfStockException;
import main.java.com.store.exception.StoreException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
Order reprezintă o comandă plasată de un client și conține
produsele comandate, statusul și data comenzii.
Clasa este responsabilă pentru calcularea totalului
și pentru actualizarea stării comenzii în funcție de regulile definite.
 */

public class Order {
    private static int nextId = 1;
    private final int id;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private final LocalDate orderDate;

    public Order(Customer customer){
        this.id = nextId++;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void addItem(Product product,
                        int quantity) throws InvalidQuantityException, OutOfStockException{
        if (quantity <= 0){
            throw new InvalidQuantityException(quantity);
        }
        product.decreaseStock(quantity);
        items.add(new OrderItem(product,quantity));
    }

    public double getTotal(){
        return items.stream().mapToDouble(OrderItem::getSubTotal).sum();
    }

    public void updateStatus(OrderStatus newStatus) throws StoreException{
        switch (status){
            case PENDING -> {
                if (newStatus == OrderStatus.PROCESSING || newStatus == OrderStatus.CANCELLED){
                    status = newStatus;
                }
                else {
                    throw new StoreException("Invalid status transition");
                }
            }
            case PROCESSING -> {
                if (newStatus == OrderStatus.SHIPPED || newStatus == OrderStatus.CANCELLED){
                    status = newStatus;
                }
                else {
                    throw new StoreException("Invalid status transition");
                }
            }
            case SHIPPED -> {
                if (newStatus == OrderStatus.DELIVERED){
                    status = newStatus;
                }
                else {
                    throw new StoreException("Invalid status transition");
                }
            }
            case DELIVERED,CANCELLED -> {
                throw new StoreException("Cannot change from " + status +
                        " to another status");
            }
        }
    }

    public void displayInfo() {
        System.out.println("Order ID: " + id + ", " +
                           "Customer: " + customer.getName() + ", " +
                           "Total: " + getTotal() + ", Status: " + status);
    }
}
