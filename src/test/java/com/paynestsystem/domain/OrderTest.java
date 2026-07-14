package com.paynestsystem.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    void emptyOrderShouldHaveZeroTotal() {
        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Order order = new Order(101, customer);

        assertEquals(0.0, order.calculateTotal());
    }

    @Test
    void orderWithOneItemShouldCalculateCorrectTotal() {
        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Product laptop = new Product(1, "Laptop", 12000);

        Order order = new Order(101, customer);
        order.addItem(laptop, 1);

        assertEquals(12000.0, order.calculateTotal());
    }

    @Test
    void orderWithMultipleItemsShouldCalculateGrandTotal() {
        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Product laptop = new Product(1, "Laptop", 12000);
        Product mouse = new Product(2, "Wireless Mouse", 350);

        Order order = new Order(101, customer);
        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        assertEquals(12700.0, order.calculateTotal());
    }
}