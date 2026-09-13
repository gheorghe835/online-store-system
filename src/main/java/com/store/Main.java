package main.java.com.store;

import main.java.com.store.exception.*;
import main.java.com.store.model.*;
import main.java.com.store.service.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Clasa Main reprezintă punctul de pornire al aplicației și
conține meniul interactiv prin care utilizatorul poate
folosi funcționalitățile sistemului de magazin online.

Aceasta permite adăugarea produselor și clienților,
afișarea informațiilor și testarea operațiilor precum
plasarea comenzilor sau actualizarea statusului acestora.

De asemenea, prin intermediul instrucțiunii switch,
clasa Main organizează clar opțiunile din meniu și
gestionează excepțiile apărute în timpul execuției.
 */

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            printHeader("ONLINE STORE MENU");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product stock: ");
                    int stock = scanner.nextInt();
                    System.out.print("Enter product category(" +
                            "1 - ELECTRONICS," +
                            "2 - CLOTHING," +
                            "3 - BOOKS," +
                            "4 - FOOD," +
                            "5 - TOYS): ");
                    int categoryChoice = scanner.nextInt();
                    Category category = Category.values()[categoryChoice - 1];
                    Product product = store.addProduct(name,price,stock,category);
                    System.out.println("Product added: " + product.getName());
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter customer email: ");
                    String email = scanner.next();
                    Customer customer = store.addCustomer(customerName,email);
                    System.out.println("Customer added: " + customer.getName());
                    break;

                case 3:
                    printAdditionalInfo("Store Catalog");
                    store.displayCatalog();
                    break;

                case 4:
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    try {
                        Customer cust = store.findCustomerById(customerId);
                        System.out.print("Enter product ID to add to cart:");
                        int productId = scanner.nextInt();
                        Product prod = store.findProductById(productId);
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        cust.getCart().addItem(prod,quantity);
                        System.out.println("Item added to cart.");
                    }
                    catch (CustomerNotFoundException |
                            ProductNotFoundException |
                            InvalidQuantityException |
                            OutOfStockException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter customer ID: ");
                    int orderCustomerId = scanner.nextInt();
                    try {
                        Customer orderCustomer = store.findCustomerById(orderCustomerId);

                        if (orderCustomer.getCart().isEmpty()) {
                            throw new EmptyCartException();
                        }

                        System.out.println("Choose payment method:");
                        System.out.println("1 - CREDIT_CARD");
                        System.out.println("2 - DEBIT_CARD");
                        System.out.println("3 - PAYPAL");
                        System.out.println("4 - CASH_ON_DELIVERY");
                        System.out.print("Choose: ");
                        int paymentChoice = scanner.nextInt();
                        PaymentMethod paymentMethod = PaymentMethod.values()[paymentChoice - 1];

                        List<OrderItem> orderItems = new ArrayList<>();
                        for (CartItem item : orderCustomer.getCart().getItems()) {
                            orderItems.add(new OrderItem(item.product(), item.quantity()));
                        }

                        Order order = store.placeOrder(orderCustomerId, orderItems, paymentMethod);

                        System.out.println("Order placed: #" + order.getId());
                        System.out.println("Payment method: " + paymentMethod);
                        System.out.println("Total: " + order.getTotal());

                        orderCustomer.getCart().clear();

                    } catch (CustomerNotFoundException |
                             EmptyCartException |
                             OutOfStockException |
                             InvalidQuantityException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Enter order ID to update: ");
                    int orderId = scanner.nextInt();
                    System.out.print("Enter new status ( " +
                            "1 - PENDING," +
                            "2 - PROCESSING," +
                            "3 - SHIPPED," +
                            "4 - DELIVERED," +
                            "5 - CANCELLED");
                    int statusChoice = scanner.nextInt();
                    OrderStatus newStatus = OrderStatus.values()[statusChoice - 1];
                    try {
                        Order orderToUpdate = store.findOrderById(orderId);                        orderToUpdate.updateStatus(newStatus);
                        System.out.println("Order status updated to: " + newStatus);
                    }
                    catch (StoreException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    printAdditionalInfo("All Orders");
                    store.displayAllOrders();
                    break;

                case 8:
                    printAdditionalInfo("All Customers");
                    store.displayCustomers();
                    break;

                case 0:
                    System.out.println("Exiting ... ");
                    printFooter();
                    break;
            }
        }
        while (choice != 0);


        scanner.close();
    }

    public static void printHeader(String title){
        System.out.println("====================================");
        System.out.println("        " + title);
        System.out.println("====================================");
        System.out.println("1. Add Product");
        System.out.println("2. Add Customer");
        System.out.println("3. Display Catalog");
        System.out.println("4. Add Item to Cart");
        System.out.println("5. Place Order");
        System.out.println("6. Update Order Status");
        System.out.println("7. Display All Orders");
        System.out.println("8. Display Customers");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public static void printAdditionalInfo(String title){
        System.out.println("--- " + title + " ---");
    }

    public static void printFooter() {
        System.out.println("====================================");
        System.out.println("Program finished successfully.");
    }
}
