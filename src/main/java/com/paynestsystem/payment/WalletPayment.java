package com.paynestsystem.payment;

import java.math.BigDecimal;

public class WalletPayment implements PaymentMethod {

    private BigDecimal balance;

    public WalletPayment(BigDecimal startingBalance) {
        this.balance = startingBalance;
    }

    @Override
    public boolean processPayment(double amount) {
        BigDecimal charge = BigDecimal.valueOf(amount);

        if (charge.compareTo(balance) > 0) {
            System.out.println("Wallet payment declined due to insufficient funds.");
            return false;
        }

        balance = balance.subtract(charge);
        System.out.println("Processing wallet payment of R" + String.format("%.2f", amount));
        return true;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String getPaymentType() {
        return "WALLET";
    }
}