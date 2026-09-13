package main.java.com.store.model;

/*
Customer reprezintă un client înregistrat în
magazinul online, având informații precum numele,
 emailul și coșul de cumpărături asociat.
 Această clasă este folosită pentru a identifica
 utilizatorii și pentru a gestiona comenzile lor.
 */

public class Customer {
    private static int nextId = 1;
    private final int id;
    private final String name;
    private final String email;
    private final Cart cart;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Cart getCart() {
        return cart;
    }

    public Customer(String name,
                    String email){
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.cart = new Cart();
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
    }

}
