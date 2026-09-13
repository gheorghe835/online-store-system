package main.java.com.store.model;

import main.java.com.store.exception.InvalidQuantityException;
import main.java.com.store.exception.OutOfStockException;

import java.util.ArrayList;
import java.util.List;

/*
Cart gestionează produsele selectate de un
client înainte de finalizarea comenzii. Ea permite
adăugarea, eliminarea și afișarea item-urilor,
precum și calcularea totalului coșului.
 */

public class Cart {
    private final List<CartItem> items;

    public Cart(){
        this.items = new ArrayList<>();
    }

    public void addItem(Product product,
                        int quantity) throws InvalidQuantityException, OutOfStockException {
        if (quantity <= 0){
            throw new InvalidQuantityException(quantity);
        }
        product.decreaseStock(quantity);
        items.add(new CartItem(product,quantity));
    }

    public void removeItem(Product product){
        items.removeIf(cartItem -> cartItem.product().getId() == product.getId());
    }

    public double getTotal(){
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public List<CartItem> getItems(){
        return items;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void clear(){
        items.clear();
    }

    public void displayCart() {
        for (CartItem item : items) {
            System.out.println(item.quantity() + " x " + item.product().getName() +
                                                 " = " + item.getSubtotal());
        }
        System.out.println("Total: " + getTotal());
    }
}
