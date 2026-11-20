package com.paynestsystem.payment;
import java.time.YearMonth;

public class CardPayment implements PaymentMethod {

    public CardPayment(String cardNumber, YearMonth expiry, String cvv){
        if(cardNumber == null || !cardNumber.replace(" ","").matches("[0-9]{16}")){
            throw new IllegalArgumentException("Card number must contain 16 digits");
        }
        if(expiry == null || expiry.isBefore(YearMonth.now())){
            throw new IllegalArgumentException("Card expiry must be this month or later");
        }

        if(cvv == null|| !cvv.matches("[0-9]{3}")){
            throw new IllegalArgumentException("cvv must contain3 digits");
        }
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing card payment of R" + String.format("%.2f", amount));
        return true;
    }

    @Override
    public String getPaymentType(){
        return "CARD";
    }
}