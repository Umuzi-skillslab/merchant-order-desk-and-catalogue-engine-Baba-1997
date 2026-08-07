
package com.paynestsystem.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.paynestsystem.presentation.OrderSummaryPrinter;

public class OrderTest {

    @Test
    void emptyOrderShouldHaveZeroTotal() {

        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Order order = new Order(101, customer);

        assertEquals(
                BigDecimal.ZERO,
                order.calculateTotal()
        );
    }

    @Test
    void orderWithOneItemShouldCalculateCorrectTotal() {

        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Product laptop = new Product(
                1,
                "Laptop",
                BigDecimal.valueOf(12000)
        );

        Order order = new Order(101, customer);

        order.addItem(laptop, 1);

        assertEquals(
                BigDecimal.valueOf(12000),
                order.calculateTotal()
        );
    }

    @Test
    void orderWithMultipleItemsShouldCalculateGrandTotal() {

        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Product laptop = new Product(
                1,
                "Laptop",
                BigDecimal.valueOf(12000)
        );

        Product mouse = new Product(
                2,
                "Wireless Mouse",
                BigDecimal.valueOf(350)
        );

        Order order = new Order(101, customer);

        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        assertEquals(
                BigDecimal.valueOf(12700),
                order.calculateTotal()
        );
    }

    @Test
    void shouldRejectZeroQuantity() {

        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Product laptop = new Product(
                1,
                "Laptop",
                BigDecimal.valueOf(12000)
        );

        Order order = new Order(101, customer);

        assertThrows(
                IllegalArgumentException.class,
                () -> order.addItem(laptop, 0)
        );
    }

    @Test
    void shouldRejectNegativeQuantity() {

        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Product laptop = new Product(
                1,
                "Laptop",
                BigDecimal.valueOf(12000)
        );

        Order order = new Order(101, customer);

        assertThrows(
                IllegalArgumentException.class,
                () -> order.addItem(laptop, -1)
        );
    }

    @Test
    void shouldProduceCorrectOrderSummary() {

        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Product laptop = new Product(
                1,
                "Laptop",
                BigDecimal.valueOf(12000)
        );

        Product mouse = new Product(
                2,
                "Wireless Mouse",
                BigDecimal.valueOf(350)
        );

        Order order = new Order(101, customer);

        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        OrderSummaryPrinter printer = new OrderSummaryPrinter();

        String summary = printer.print(order);

        assertTrue(
                summary.contains("Order ID: 101")
        );

        assertTrue(
                summary.contains("Customer: Chuma Gqodi")
        );

        assertTrue(
                summary.contains("Laptop")
        );

        assertTrue(
                summary.contains("x 1")
        );

        assertTrue(
                summary.contains("Wireless Mouse")
        );

        assertTrue(
                summary.contains("x 2")
        );

        assertTrue(
                summary.contains("Grand Total:")
        );

        assertEquals(
                BigDecimal.valueOf(12700),
                order.calculateTotal()
        );
    }

    @Test
    void getItemsShouldReturnUnmodifiableList() {

        Customer customer = new Customer(
                1,
                "Chuma Gqodi",
                "chuma.gqodi@umuzi.org"
        );

        Product laptop = new Product(
                1,
                "Laptop",
                BigDecimal.valueOf(12000)
        );

        Order order = new Order(101, customer);

        order.addItem(laptop, 1);

        List<OrderItem> items = order.getItems();

        assertEquals(
                1,
                items.size()
        );

        assertThrows(
                UnsupportedOperationException.class,
                () -> items.clear()
        );

        assertEquals(
                1,
                order.getItems().size()
        );
    }
}





