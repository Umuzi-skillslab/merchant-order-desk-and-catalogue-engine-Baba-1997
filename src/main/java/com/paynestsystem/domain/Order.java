package com.paynestsystem.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private Customer customer;
    private List<OrderItem> items;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
    }

    public double calculateTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.calculateTotal();
        }

        return total;
    }

    public void printSummary() {
        System.out.println("Order ID: " + id);
        System.out.println("Customer: " + customer.getName());

        for (OrderItem item : items) {
            System.out.println(
                item.getProduct().getName() +
                " x " +
                item.getQuantity() +
                " = R" +
                item.calculateTotal()
            );
        }

        System.out.println("Grand Total: R" + calculateTotal());
    }
}