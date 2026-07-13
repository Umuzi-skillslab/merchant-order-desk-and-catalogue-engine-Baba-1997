package com.paynestsystem.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderItemTest {

    @Test
    void shouldCalculateTotalCorrectly() {
        Product product = new Product(1, "Laptop", 12000);

        OrderItem item = new OrderItem(product, 2);

        assertEquals(24000, item.calculateTotal());
    }

    @Test
    void shouldRejectZeroQuantity() {
        Product product = new Product(1, "Laptop", 12000);

        assertThrows(
            IllegalArgumentException.class,
            () -> new OrderItem(product, 0)
        );
    }

    @Test
    void shouldRejectNegativeQuantity() {
        Product product = new Product(1, "Laptop", 12000);

        assertThrows(
            IllegalArgumentException.class,
            () -> new OrderItem(product, -1)
        );
    }
}