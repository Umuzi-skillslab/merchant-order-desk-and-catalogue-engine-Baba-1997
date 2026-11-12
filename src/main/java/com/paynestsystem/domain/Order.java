
package com.paynestsystem.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.paynestsystem.payment.PaymentMethod;
import com.paynestsystem.payment.PaymentProcessor;

public final class Order {

    private final int id;
    private final Customer customer;
    private final List<OrderItem> items;

    public Order(int id, Customer customer) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Order ID must be greater than 0"
            );
        }

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer cannot be null"
            );
        }

        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {

        if (product == null) {
            throw new IllegalArgumentException(
                    "Product cannot be null"
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than 0"
            );
        }

        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
    }

    public BigDecimal calculateTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            total = total.add(item.calculateTotal());
        }

        return total;
    }
    public boolean checkout(PaymentMethod paymentMethod) {
        if(paymentMethod == null){
            throw new IllegalArgumentException("Payment method is required");
        }
        BigDecimal total = calculateTotal();
        PaymentProcessor processor = new PaymentProcessor();
       return processor.processPayment(paymentMethod, total);
}
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }
}

