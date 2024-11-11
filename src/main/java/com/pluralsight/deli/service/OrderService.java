package com.pluralsight.deli.service;
import com.pluralsight.deli.model.Order;
import com.pluralsight.deli.model.Product;

public class OrderService {

    public double calculateTotal(Order order) {
        double total = 0;
        for (Product item : order.getItems()) {
            total += item.calculateProductTotal();
        }
        return total;
    }

}
