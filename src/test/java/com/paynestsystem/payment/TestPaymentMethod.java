package com.paynestsystem.payment;

public class TestPaymentMethod implements PaymentMethod {

    private double receivedAmount;

    @Override
    public boolean processPayment(double amount) {
        this.receivedAmount = amount;
        return true;
    }

    @Override
    public String getPaymentType() {
        return "TEST";
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }
}