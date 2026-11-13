package com.paynestsystem.payment;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class PaymentMethodTest {
    @Test
    void checkoutCanUseDifferentPaymentMethods(){
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);

        Product product = new Product(1, "Test Product", BigDecimal.valueOf(500));
        order.addItem(product, 3);

         PaymentMethod card = new CardPayment("4222 1111 3333 5555", YearMonth.now().plusMonths(6), "012");
        PaymentMethod eft = new EftPayment();
        PaymentMethod wallet = new WalletPayment();

        assertTrue(order.checkout(card));
        assertTrue(order.checkout(eft));
        assertTrue(order.checkout(wallet));

        assertEquals("EFT", eft.getPaymentType());
        assertEquals("WALLET", wallet.getPaymentType());
        assertEquals("CARD", card.getPaymentType());

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

    @Test
    void checkoutReturnsFalseWhenPaymentFails(){
        Customer customer = new Customer(1,"Test Customer", "test@umuzi.org");
        Order order = new Order(1,customer);
        order.addItem(new Product(1, "Test product", BigDecimal.valueOf(500)), 1);

        PaymentMethod declinedPayment = new PaymentMethod(){
            @Override
            public boolean processPayment(BigDecimal amount){
                return false;
            }
            @Override
            public String getPaymentType(){
                return "TEST";
            }
        };
        assertFalse(order.checkout(declinedPayment));
    }
    @Test
    void checkoutRejectMissingPaymentMethod(){
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);
        assertThrows(IllegalArgumentException.class,()-> order.checkout(null));
    }

    @Test
    void rejectsCardWithInvalidCvv(){
        assertThrows(IllegalArgumentException.class, ()->{
            new CardPayment(
                "4222 1111 3333 5555",
                YearMonth.now().plusMonths(6),
                "12"
            );
        });
    }

    @Test
    void rejectsCardWithShortCardNumber(){
        assertThrows(IllegalArgumentException.class, ()->{
            new CardPayment(
                "4222 1111 3333",
                YearMonth.now().plusMonths(6),
                "012"
            );
        });
    }

    @Test
    void rejectsExpiredCard(){
        YearMonth expiredMonth = YearMonth.now().minusMonths(1);

        assertThrows(IllegalArgumentException.class, ()->{
            new CardPayment(
                "4222 1111 3333 5555",
                expiredMonth,
                "012"
            );
        });
    }

    @Test
    void checkoutRejectsZeroTotal(){
        Customer customer = new Customer(1, "Test Customer", "test@umuzi.org");
        Order order = new Order(1, customer);
        order.addItem(new Product(1,"Free item", BigDecimal.ZERO), 1);

        assertThrows(IllegalArgumentException.class, ()-> order.checkout(new EftPayment()));
    }

   
}


