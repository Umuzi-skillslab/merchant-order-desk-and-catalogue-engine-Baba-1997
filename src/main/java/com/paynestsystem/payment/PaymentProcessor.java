package com.paynestsystem.payment;

import java.math.BigDecimal;

public class PaymentProcessor {

    public boolean processPayment(PaymentMethod paymentMethod, BigDecimal amount) {
        boolean success = paymentMethod.processPayment(amount);

        if (success) {
            System.out.println("Payment successful via " + paymentMethod.getPaymentType());
            System.out.println("Amount: R" + String.format("%.2f", amount));
        } else {
            System.out.println("Payment failed via " + paymentMethod.getPaymentType());
        }

        return success;
    }
}