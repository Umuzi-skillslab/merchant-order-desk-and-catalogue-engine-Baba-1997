package com.paynestsystem.payment;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class PaymentMethodTest {
    @Test
    void checkoutCanUseDifferentPaymentMethods(){
        PaymentMethod card = new CardPayment();
        PaymentMethod eft = new EftPayment();
        PaymentMethod wallet = new WalletPayment();

        assertTrue(card.processPayment(BigDecimal.valueOf(12400)));
        assertTrue(eft.processPayment(BigDecimal.valueOf(12400)));
        assertTrue(wallet.processPayment(BigDecimal.valueOf(12400)));
        assertEquals("CARD", card.getPaymentType());
        assertEquals("EFT", eft.getPaymentType());
        assertEquals("WALLET", wallet.getPaymentType());

    }
    @Test
    void checkoutUsesRealOrderTotal() {
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);

        Product product = new Product(1, "Test Product", BigDecimal.valueOf(500));
        order.addItem(product, 3);

        TestPaymentMethod test = new TestPaymentMethod();
        boolean success = order.checkout(test);

        assertTrue(success);
        assertEquals(order.calculateTotal(), test.getReceivedAmount());
    }
}

