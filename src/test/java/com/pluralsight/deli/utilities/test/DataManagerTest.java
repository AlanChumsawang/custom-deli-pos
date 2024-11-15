package com.pluralsight.deli.utilities.test;

import com.pluralsight.deli.model.*;
import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.util.DataManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataManagerTest {
    private Order order;
    private DataManager dataManager;
    private String receiptFilePath;
    private String databaseFilePath = "src/main/resources/transaction-history.csv";

    @BeforeEach
    void setUp() {
        Sandwich sandwich = new Sandwich("Sandwich", SandwichSize.SMALL, BreadType.WHEAT, false);
        sandwich.addPremiumTopping(PremiumToppings.BACON);
        Drink drink = new Drink("Drink", DrinkSize.SMALL);
        order = new Order("TestName");
        order.addItem(sandwich);
        order.addItem(drink);

        dataManager = new DataManager();
        dataManager.loadFromDatabase();

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        String formattedToday = today.format(formatter);
        receiptFilePath = "src/main/resources/receipts/" + formattedToday + ".txt";

    }

    @Test
    void testReceiptGenerator() throws IOException {
        dataManager.receiptGenerator(order);

        // Check if the file was created
        assertTrue(Files.exists(Paths.get(receiptFilePath)));

        try (BufferedReader reader = new BufferedReader(new FileReader(receiptFilePath))) {
            assertEquals("Order Number: " + dataManager.getOrderNumber(), reader.readLine());
            assertEquals("Customer Name: TestName", reader.readLine());
            assertEquals("Items: ", reader.readLine());
            assertEquals("Sandwich - $6.50", reader.readLine());
            assertEquals("Drink - $2.00", reader.readLine());
            assertEquals("Total: $8.50", reader.readLine());
        }
    }

    @Test
    void testSaveToDatabase() throws IOException {
        // Save the order to the database
        dataManager.saveToDatabase(order);

        // Read the last line of the database file and verify the contents
        String lastLine = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(databaseFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
        }

        String[] data = lastLine.split("[|]");
        assertEquals(dataManager.getOrderNumber(), Integer.parseInt(data[0]), "Order number should match.");
        assertEquals(order.getCustomerName(), data[1], "Customer name should match.");
    }
}
