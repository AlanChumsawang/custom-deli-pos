package com.pluralsight.deli.controller;

import com.pluralsight.deli.model.*;
import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.service.interfaces.*;
import com.pluralsight.deli.service.impl.*;
import com.pluralsight.deli.util.DataManager;
import java.util.Scanner;

public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private final SandwichService sandwichService;
    private final OrderService orderService;
    private Order currentOrder;

    public UI() {
        this.sandwichService = new SandwichServiceImpl(scanner);
        this.orderService = new OrderServiceImpl();
    }

    public void display() {
        boolean running = true;
        while (running) {
            running = displayHomeMenu();
        }
    }

    private boolean displayHomeMenu() {
        System.out.print(MenuPrompts.getHomeMenu());
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                displayOrderMenu();
                break;
            case 0:
                System.out.println("Goodbye!");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    public void displayOrderMenu() {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        currentOrder = new Order(customerName);
        boolean ordering = true;
        while (ordering) {
            System.out.println(MenuPrompts.getOrderMenu());
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addChips();
                    break;
                case 3:
                    checkout();
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addSandwich() {
        SandwichSize size = sandwichService.selectSandwichSize();
        if (size == null) {
            sandwichService.selectSandwichSize();
        }
        BreadType breadType = sandwichService.selectBreadType();
        System.out.println("Would you like your sandwich toasted? (1: Yes, 2: No)");
        boolean isToasted = ( Integer.parseInt(scanner.nextLine()) ) == 1;
        Sandwich sandwich = (new Sandwich("Custom Sandwich", size, breadType, isToasted, false));
        sandwichService.selectRegularToppings(sandwich);
        sandwichService.selectPremiumToppings(sandwich);
    }


    private void addChips() {
        System.out.print(MenuPrompts.chips);
        int choice = Integer.parseInt(scanner.nextLine());
        String chipsName;
        switch (choice) {
            case 1:
                chipsName = "Lays Original";
                break;
            case 2:
                chipsName = "Lays BBQ";
                break;
            case 3:
                chipsName = "Lays Sour Cream & Onion";
                break;
            case 4:
                chipsName = "Doritos Nacho Cheese";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
        Chips chips = new Chips(chipsName);
        currentOrder.addItem(chips);
        System.out.println("Chips added to order.");

    }


    private void checkout() {
        DataManager dataManager = new DataManager();
        System.out.println("Order Summary:");
        for (Product item : currentOrder.getItems()) {
            System.out.println(item.getName() + ": $" + item.calculateProductTotal());
        }
        System.out.println("Thank you for your order, " + currentOrder.getCustomerName() + "!");
        dataManager.receiptGenerator(currentOrder);
    }
}