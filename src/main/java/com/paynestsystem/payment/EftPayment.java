package com.paynestsystem.payment;

import java.math.BigDecimal;

public class EftPayment implements PaymentMethod {
    @Override
    public boolean processPayment(BigDecimal amount) {
        System.out.println("Processing EFT payment for R" + amount);
        return true;
    }
    @Override
    public String getPaymentType() {
        return "EFT";
    }
}
