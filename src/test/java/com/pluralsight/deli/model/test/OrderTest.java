package com.pluralsight.deli.model.test;

import com.pluralsight.deli.model.*;
import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private Order order;
    private List<Product> items = new ArrayList<>();
    private OrderService orderService = new OrderService();

    // Create a new Order object with a Sandwich and a Drink
    @BeforeEach
    void setUp() {
        Sandwich sandwich = new Sandwich("Sandwich", SandwichSize.SMALL, BreadType.WHEAT, true, false);
        sandwich.addPremiumTopping(PremiumToppings.BACON);
        Drink drink = new Drink("Drink", DrinkSize.SMALL);
        items.add(sandwich);
        items.add(drink);

        order = new Order(items, "Rick Mathurin", "1111");
    }

    // Test the calculateTotal method
    @Test
    void testCalculateTotal() {
        assertEquals(8.50, orderService.calculateTotal(order));
    }

    // Test adding chips to the order and recalculating the total
    @Test
    void testAddItem() {
        Chips chips = new Chips("Chips");
        order.addItem(chips);
        assertEquals(3, order.getItems().size());
        assertEquals(10.00, orderService.calculateTotal(order));
    }
}