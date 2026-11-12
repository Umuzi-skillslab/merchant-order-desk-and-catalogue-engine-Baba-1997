package com.paynestsystem.payment;

import java.math.BigDecimal;

public class EftPayment implements PaymentMethod {

    @Override
    public boolean  processPayment(BigDecimal amount){
     System.out.println("processing eft payment of amount " + amount);
        return true;
    }

    @Override
    public String getPaymentType(){
        return "EFT";
    }
}