package com.pluralsight.deli.util;

import com.pluralsight.deli.model.*;
import com.pluralsight.deli.service.impl.OrderServiceImpl;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    LocalDateTime today = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
    String formattedToday = today.format(formatter);
    String receiptFilepath = "src/main/resources/receipts/" + formattedToday + ".txt";
    String databaseFilepath = "src/main/resources/transaction-history" + ".csv";
    OrderServiceImpl orderService = new OrderServiceImpl();
    private List<Order> orders = new ArrayList<>(); // Initialize the orders list

    public void receiptGenerator(Order order) {
        try (FileWriter writer = new FileWriter(receiptFilepath)) {
            writer.write("Order Number: " + getOrderNumber() + "\n");
            writer.write("Customer Name: " + order.getCustomerName() + "\n");
            writer.write("Items: \n");
            for (Product item : order.getItems()) {
                writer.write(item.getName() + " - $" + String.format("%.2f", item.calculateProductTotal()) + "\n");
            }
            writer.write("Total: $" + String.format("%.2f", orderService.calculateTotal(order)) + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToDatabase(Order order) {
        try (FileWriter writer = new FileWriter(databaseFilepath, true)) {
            writer.write(getOrderNumber() + "|" + order.getCustomerName() + "|" + String.format("%.2f", orderService.calculateTotal(order)) + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromDatabase() {
        // Load transactions
        try {
            BufferedReader reader = new BufferedReader(new FileReader(databaseFilepath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("[|]");
                int orderNumber = Integer.parseInt(data[0]);
                String customerName = data[1];
                Order order = new Order(customerName);
                orders.add(order);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getOrderNumber() {
        return 1110 + orders.size() + 1;
    }
}