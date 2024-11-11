package com.pluralsight.deli.model;

import java.util.List;

public class Order {
    private List<Product> items;
    private final String customerName;
    private final String orderNumber;

    public Order(List<Product> items, String customerName, String orderNumber) {
        this.items = items;
        this.customerName = customerName;
        this.orderNumber = orderNumber;
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public List<Product> getItems() {
        return items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

}
