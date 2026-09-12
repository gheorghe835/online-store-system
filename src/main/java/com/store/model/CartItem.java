package main.java.com.store.model;

import model.Product;

/*
CartItem reprezintă un produs aflat în coș
împreună cu cantitatea aleasă. El conține și o
metodă care calculează subtotalul pentru acel produs.
 */

public record CartItem(Product product,int quantity) {
    public double getSubtotal(){
        return product.getPrice() * quantity;
    }
}
