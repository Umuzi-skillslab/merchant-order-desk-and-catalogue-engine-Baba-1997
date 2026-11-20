package com.paynestsystem.payment;

public class PaymentProcessor {

    public boolean processPayment(PaymentMethod paymentMethod, double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be finite and greater than zero");
        }

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