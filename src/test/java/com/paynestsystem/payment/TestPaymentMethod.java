package com.paynestsystem.payment;

import java.math.BigDecimal;

public class TestPaymentMethod implements PaymentMethod {
   private BigDecimal receivedAmount;

   @Override
   public boolean processPayment(BigDecimal amount) {
    this.receivedAmount = amount;
    return true;

   }
   @Override
   public String getPaymentType() {
    return "TEST";
   }

 public BigDecimal getReceivedAmount(){
    return receivedAmount;
 }

}
