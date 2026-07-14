package com.paynestsystem.service;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;

public class OrderService {

    public Order createOrder(int orderId, Customer customer) {
        return new Order(orderId, customer);
    }
}