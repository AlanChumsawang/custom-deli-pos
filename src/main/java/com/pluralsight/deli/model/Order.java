package com.pluralsight.deli.model;

import com.pluralsight.deli.util.DataManager;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> items;
    private final String customerName;
    private final String orderNumber;
    private static final DataManager dataManager = new DataManager();

    public Order(String customerName) {
        this.items = new ArrayList<Product>();
        this.customerName = customerName;
        this.orderNumber = dataManager.getOrderNumber();
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