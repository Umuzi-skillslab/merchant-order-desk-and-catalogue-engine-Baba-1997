package com.paynestsystem.payment;

import java.math.BigDecimal;

public class CardPayment implements PaymentMethod {
    @Override
   public boolean processPayment(BigDecimal amount) {
    System.out.println("Processing card payment for R" + amount);
    return true;
   }

   @Override
   public  String getPaymentType() {
    return "CARD";
   }
}
