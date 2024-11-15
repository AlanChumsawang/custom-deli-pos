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


    public UI() {
        DataManager dataManager = new DataManager();
        this.sandwichService = new SandwichServiceImpl(scanner);
        this.orderService = new OrderServiceImpl(dataManager);
        dataManager.setOrderService((OrderServiceImpl) this.orderService);
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
        boolean nameNotNull = false;
        String customerName = "";
        while (!nameNotNull) {
            System.out.print("Enter your name: ");
            customerName = scanner.nextLine();
            if (!customerName.isEmpty()) {
                nameNotNull = true;
            } else {
                System.out.println("Name cannot be empty. Please try again.");
            }
        }
        Order currentOrder = new Order(customerName);
        boolean ordering = true;
        while (ordering) {
            System.out.print("\n\n\n\n" + orderService.orderFormatter(currentOrder) + "\n\n" + MenuPrompts.getOrderMenu());
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
                    orderService.checkout(currentOrder);
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
}
