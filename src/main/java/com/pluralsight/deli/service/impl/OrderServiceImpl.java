package com.pluralsight.deli.service.impl;

import com.pluralsight.deli.model.Order;
import com.pluralsight.deli.model.Product;
import com.pluralsight.deli.service.interfaces.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public double calculateTotal(Order order) {
        double total = 0;
        for (Product item : order.getItems()) {
            total += item.calculateProductTotal();
        }
        return total;
    }
}