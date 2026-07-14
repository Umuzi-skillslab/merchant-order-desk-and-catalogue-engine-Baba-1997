package com.paynestsystem.app;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;
import com.paynestsystem.service.OrderService;

public class PayNestApplication {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        Product laptop = new Product(1, "Laptop", 12000);
        Product mouse = new Product(2, "Wireless Mouse", 350);

        Customer customer = new Customer(1, "Chuma Gqodi", "chuma.gqodi@umuzi.org");

        Order order = orderService.createOrder(101, customer);

        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        order.printSummary();
    }
}