package com.paynestsystem.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;

public class PaymentMethodTest {

    @Test
    void checkoutCanUseDifferentPaymentMethods() {
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);
        order.addItem(new Product(1, "Test Product", BigDecimal.valueOf(500)), 3);

        PaymentMethod card = new CardPayment(
                "4222 1111 3333 5555",
                YearMonth.now().plusMonths(6),
                "012");
        PaymentMethod eft = new EftPayment();
        PaymentMethod wallet = new WalletPayment(new BigDecimal("1500.00"));

        assertTrue(order.checkout(card));
        assertTrue(order.checkout(eft));
        assertTrue(order.checkout(wallet));

        assertEquals("CARD", card.getPaymentType());
        assertEquals("EFT", eft.getPaymentType());
        assertEquals("WALLET", wallet.getPaymentType());
    }

    @Test
    void checkoutUsesRealOrderTotal() {
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);
        order.addItem(new Product(1, "Test Product", BigDecimal.valueOf(500)), 3);

        TestPaymentMethod testPayment = new TestPaymentMethod();

        assertTrue(order.checkout(testPayment));
        assertEquals(order.calculateTotal().doubleValue(), testPayment.getReceivedAmount());
    }

    @Test
    void checkoutReturnsFalseWhenPaymentFails() {
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);
        order.addItem(new Product(1, "Test Product", BigDecimal.valueOf(500)), 1);

        PaymentMethod declinedPayment = new PaymentMethod() {
            @Override
            public boolean processPayment(double amount) {
                return false;
            }

            @Override
            public String getPaymentType() {
                return "TEST";
            }
        };

        assertFalse(order.checkout(declinedPayment));
    }

    @Test
    void checkoutRejectsMissingPaymentMethod() {
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);

        assertThrows(IllegalArgumentException.class, () -> order.checkout(null));
    }

    @Test
    void rejectsCardWithInvalidCvv() {
        assertThrows(IllegalArgumentException.class, () -> new CardPayment(
                "4222 1111 3333 5555",
                YearMonth.now().plusMonths(6),
                "12"));
    }

    @Test
    void rejectsCardWithShortCardNumber() {
        assertThrows(IllegalArgumentException.class, () -> new CardPayment(
                "4222 1111 3333",
                YearMonth.now().plusMonths(6),
                "012"));
    }

    @Test
    void rejectsExpiredCard() {
        YearMonth expiredMonth = YearMonth.now().minusMonths(1);

        assertThrows(IllegalArgumentException.class, () -> new CardPayment(
                "4222 1111 3333 5555",
                expiredMonth,
                "012"));
    }

    @Test
    void checkoutRejectsZeroTotal() {
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);
        order.addItem(new Product(1, "Free item", BigDecimal.ZERO), 1);

        assertThrows(IllegalArgumentException.class, () -> order.checkout(new EftPayment()));
    }

    @Test
    void checkoutDeclinesWhenWalletBalanceIsTooLow() {
        WalletPayment wallet = new WalletPayment(new BigDecimal("500.00"));
        Customer customer = new Customer(1, "Test Customer", "test@example.com");
        Order order = new Order(101, customer);
        order.addItem(new Product(1, "Test Product", new BigDecimal("600.00")), 1);

        assertFalse(order.checkout(wallet));
        assertEquals(0, new BigDecimal("500.00").compareTo(wallet.getBalance()));
    }
}