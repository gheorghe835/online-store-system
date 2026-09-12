package main.java.com.store.model;

import model.Product;

/*
OrderItem reprezintă un produs inclus
într-o comandă, împreună cu cantitatea comandată.
Ca și CartItem, acesta are o metodă care calculează
subtotalul folosind prețul produsului și cantitatea.
 */

public record OrderItem(Product product,
                        int quantity) {
    public double getSubTotal(){
        return product.getPrice() * quantity;
    }
}
