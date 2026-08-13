package com.paynestsystem.payment;

import java.math.BigDecimal;

public class WalletPayment implements PaymentMethod {
    @Override
    public boolean processPayment(BigDecimal amount) {
        System.out.println("Processing wallet payment for R" + amount);
        return true;
    }
    @Override
    public String getPaymentType() {
        return "WALLET";
    }
}
