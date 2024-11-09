package com.pluralsight.deli.model;

import java.util.List;

public class Order {
    private List<Product> items;
    private String customerName;
    private String orderNumber;

    public Order(List<Product> items, String customerName, String orderNumber) {
        this.items = items;
        this.customerName = customerName;
        this.orderNumber = orderNumber;
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public void calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.calculateProductTotal();
        }
    }
}
