package com.paynestsystem.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class OrderItemTest {

        @Test
        void shouldCreateOrderItemWithValidDetails() {
                Product product = new Product(1, "Laptop", new BigDecimal("10000.00"));
                OrderItem item = new OrderItem(product, 2);

                assertEquals(product, item.getProduct());
                assertEquals(2, item.getQuantity());
        }

        @Test
        void shouldRejectNullProduct() {
                assertThrows(
                                IllegalArgumentException.class,
                                () -> new OrderItem(null, 1));
        }

        @Test
        void shouldRejectZeroQuantity() {
                Product product = new Product(1, "Laptop", new BigDecimal("10000.00"));

                assertThrows(
                                IllegalArgumentException.class,
                                () -> new OrderItem(product, 0));
        }

        @Test
        void shouldRejectNegativeQuantity() {
                Product product = new Product(1, "Laptop", new BigDecimal("10000.00"));

                assertThrows(
                                IllegalArgumentException.class,
                                () -> new OrderItem(product, -1));
        }

        @Test
        void shouldCalculateTotalCorrectly() {
                Product product = new Product(1, "Laptop", new BigDecimal("10000.00"));
                OrderItem item = new OrderItem(product, 3);

                assertEquals(new BigDecimal("30000.00"), item.calculateTotal());
        }
}