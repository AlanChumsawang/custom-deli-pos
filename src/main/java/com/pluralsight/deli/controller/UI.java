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
    private final ChipService chipService;
    private final OrderService orderService;
    private final DrinkService drinkService;
    private Order currentOrder;
    DataManager dataManager = new DataManager();

    public UI() {
        this.sandwichService = new SandwichServiceImpl(scanner);
        this.orderService = new OrderServiceImpl();
        this.chipService = new ChipsServiceImpl(scanner);
        this.drinkService = new DrinkServiceImpl(scanner);
    }

    public void display() {
        boolean running = true;
        while (running) {
            running = displayHomeMenu();
        }
    }

    private boolean displayHomeMenu() {
        System.out.print(MenuPrompts.getHomeMenu());
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                displayOrderMenu();
                break;
            case "0":
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
            System.out.print("\n\n\n\n\n\n" + MenuPrompts.getOrderMenu());
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    currentOrder.addItem(sandwichService.createSandwich());
                    break;
                case "2":
                    currentOrder.addItem(chipService.selectChips());
                    break;
                case "3":
                    currentOrder.addItem(drinkService.selectDrink());
                    break;
                case "4":
                    checkout();
                    ordering = false;
                    break;
                case "0":
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void checkout() {
        System.out.println("Order Summary:");
        for (Product item : currentOrder.getItems()) {
            System.out.println(item.getName() + ": $" + item.calculateProductTotal());
        }
        System.out.println("Thank you for your order, " + currentOrder.getCustomerName() + "!\n════════════════════════" +
                "══════════════════════════════════════════════════════════════════════════════\n\n\n\n\n\n\n\n\n\n");
        dataManager.loadFromDatabase();
        dataManager.receiptGenerator(currentOrder);
        dataManager.saveToDatabase(currentOrder);
    }
}