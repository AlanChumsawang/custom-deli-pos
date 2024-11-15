package com.pluralsight.deli.service.impl;

import com.pluralsight.deli.model.Order;
import com.pluralsight.deli.model.Product;
import com.pluralsight.deli.service.interfaces.OrderService;
import com.pluralsight.deli.util.DataManager;

public class OrderServiceImpl implements OrderService {
    private final DataManager dataManager;

    public OrderServiceImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public double calculateTotal(Order order) {
        double total = 0;
        for (Product item : order.getItems()) {
            total += item.calculateProductTotal();
        }
        return total;
    }

    @Override
    public void checkout(Order order) {
        System.out.println("Order Summary:");
        for (Product item : order.getItems()) {
            System.out.println(item.productDetails());}
        double total = calculateTotal(order);
        System.out.println("Total: $" + String.format("%.2f", total));
        System.out.println("Thank you for your order, " + order.getCustomerName() + "!\n════════════════════════" +
                "══════════════════════════════════════════════════════════════════════════════" +
                "═══════════════════════════════════════════\n\n\n\n\n\n\n\n\n\n");
        dataManager.loadFromDatabase();
        dataManager.receiptGenerator(order);
        dataManager.saveToDatabase(order);
    }

    @Override
    public String orderFormatter(Order order) {
        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("╔════════════════════════════════════════════════════╗\n");
        orderSummary.append("║                    Order Summary                   ║\n");
        orderSummary.append("╠════════════════════════════════════════════════════╣\n");
        for (Product item : order.getItems()) {
            String itemName = item.getName();
            String itemPrice = String.format("$%.2f", item.calculateProductTotal());
            orderSummary.append(String.format("║ %-43s %6s ║\n", itemName, itemPrice));
        }
        orderSummary.append("╠════════════════════════════════════════════════════╣\n");
        double total = calculateTotal(order);
        orderSummary.append(String.format("║ %-43s %6s ║\n", "Total:", String.format("$%.2f", total)));
        orderSummary.append("╚════════════════════════════════════════════════════╝\n");
        return orderSummary.toString();
    }

}