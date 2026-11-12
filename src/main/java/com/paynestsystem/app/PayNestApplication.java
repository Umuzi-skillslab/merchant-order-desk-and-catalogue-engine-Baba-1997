
package com.paynestsystem.app;

import java.math.BigDecimal;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;
import com.paynestsystem.presentation.OrderSummaryPrinter;
import com.paynestsystem.service.OrderService;
import com.paynestsystem.payment.PaymentMethod;
import com.paynestsystem.payment.WalletPayment;

public class PayNestApplication {

        public static void main(String[] args) {

                OrderService orderService = new OrderService();

                Product laptop = new Product(
                                1,
                                "Laptop",
                                BigDecimal.valueOf(12000));

                Product mouse = new Product(
                                2,
                                "Wireless Mouse",
                                BigDecimal.valueOf(350));

                Customer customer = new Customer(
                                1,
                                "Chuma Gqodi",
                                "chuma.gqodi@umuzi.org");

                Order order = orderService.createOrder(
                                101,
                                customer);

                order.addItem(laptop, 1);
                order.addItem(mouse, 2);

                OrderSummaryPrinter printer = new OrderSummaryPrinter();

                System.out.println(printer.print(order));
                PaymentMethod paymentMethod = new WalletPayment();
               if (order.checkout(paymentMethod)) {
                 System.out.println("Order " + order.getId() + " completed.");
}

        }
}
