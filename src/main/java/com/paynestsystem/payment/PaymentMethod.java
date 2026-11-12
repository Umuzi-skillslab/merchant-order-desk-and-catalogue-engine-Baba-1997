package com.paynestsystem.payment;

public interface PaymentMethod {
      boolean processPayment(java.math.BigDecimal amount);
      
      String getPaymentType();
}