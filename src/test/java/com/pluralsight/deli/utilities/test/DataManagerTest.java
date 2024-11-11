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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataManagerTest {
    private Order order;
    private DataManager dataManager;
    private String fileName;

    @BeforeEach
    void setUp() {
        List<Product> items = new ArrayList<>();
        Sandwich sandwich = new Sandwich("Sandwich", SandwichSize.SMALL, BreadType.WHEAT, false, false);
        sandwich.addPremiumTopping(PremiumToppings.BACON);
        Drink drink = new Drink("Drink", DrinkSize.SMALL);
        items.add(sandwich);
        items.add(drink);

        order = new Order(items, "Rick Mathurin", "1111");
        dataManager = new DataManager();

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        String formattedToday = today.format(formatter);
        fileName = "src/main/resources/receipts/" + formattedToday + ".txt";
    }

    @Test
    void testReceiptGenerator() throws IOException {
        dataManager.receiptGenerator(order);

        // Check if the file was created
        assertTrue(Files.exists(Paths.get(fileName)));

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            assertEquals("Order Number: 1111", reader.readLine());
            assertEquals("Customer Name: Rick Mathurin", reader.readLine());
            assertEquals("Items: ", reader.readLine());
            assertEquals("Sandwich - $6.50", reader.readLine());
            assertEquals("Drink - $2.00", reader.readLine());
            assertEquals("Total: $8.50", reader.readLine());
        }
    }
}