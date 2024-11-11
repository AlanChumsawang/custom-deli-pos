package com.pluralsight.deli.util;

import com.pluralsight.deli.model.Order;
import com.pluralsight.deli.model.Product;
import com.pluralsight.deli.service.OrderService;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataManager {
    LocalDateTime today = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
    String formattedToday = today.format(formatter);
    String fileName = "src/main/resources/receipts/" + formattedToday + ".txt";

    public void receiptGenerator(Order order){
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Order Number: " + order.getOrderNumber() + "\n");
            writer.write("Customer Name: " + order.getCustomerName() + "\n");
            writer.write("Items: \n");
            for (Product item : order.getItems()) {
                writer.write(item.getName() + " - $" + item.calculateProductTotal() + "\n");
            }
            OrderService orderService = new OrderService();
            writer.write("Total: $" + orderService.calculateTotal(order) + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
