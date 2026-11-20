package com.paynestsystem.presentation;

import java.text.NumberFormat;
import java.util.Locale;

import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.OrderItem;

public final class OrderSummaryPrinter {

    public String print(Order order) {

        if (order == null) {
            throw new IllegalArgumentException(
              "Order cannot be null"
            );
        }

        NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance(
                        Locale.forLanguageTag("en-ZA")
                );

        StringBuilder summary = new StringBuilder();

        summary.append("Order ID: ")
                .append(order.getId())
                .append("\n");

        summary.append("Customer: ")
                .append(order.getCustomer().getName())
                .append("\n\n");

        for (OrderItem item : order.getItems()) {

            summary.append(item.getProduct().getName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" = ")
                    .append(currencyFormat.format(
                            item.calculateTotal()
                    ))
                    .append("\n");
        }

        summary.append("\nGrand Total: ")
                .append(currencyFormat.format(
                        order.calculateTotal()
                ));

        return summary.toString();
    }
}