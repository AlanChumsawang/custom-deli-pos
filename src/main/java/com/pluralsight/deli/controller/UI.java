package com.pluralsight.deli.controller;

import com.pluralsight.deli.model.*;
import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.util.DataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private Order currentOrder;

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
        SandwichSize size = selectSandwichSize();
        if (size == null) {
            selectSandwichSize();
        }
        BreadType breadType = selectBreadType();
        System.out.println("Would you like your sandwich toasted? (1: Yes, 2: No)");
        boolean isToasted = ( Integer.parseInt(scanner.nextLine()) ) == 1;
        Sandwich sandwich = (new Sandwich("Custom Sandwich", size, breadType, isToasted, false));
        selectRegularToppings(sandwich);
        selectPremiumToppings(sandwich);
    }

    private List<PremiumToppings> selectPremiumToppings(Sandwich sandwich){
    List<PremiumToppings> premiumToppings = new ArrayList<>();
    boolean done = false;
    while (!done) {
        System.out.println(MenuPrompts.premiumToppings);
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 0) {
            done = true;
        } else {
            PremiumToppings topping = switch (choice) {
                case 1 -> PremiumToppings.STEAK;
                case 2 -> PremiumToppings.HAM;
                case 3 -> PremiumToppings.SALAMI;
                case 4 -> PremiumToppings.ROAST_BEEF;
                case 5 -> PremiumToppings.CHICKEN;
                case 6 -> PremiumToppings.BACON;
                case 7 -> PremiumToppings.AMERICAN_CHEESE;
                case 8 -> PremiumToppings.SWISS_CHEESE;
                case 9 -> PremiumToppings.PROVOLONE_CHEESE;
                case 10 -> PremiumToppings.CHEDDAR_CHEESE;
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    yield null;
                }
            };
            if (topping != null) {
                sandwich.addPremiumTopping(topping);
            }
        }
    }
    return premiumToppings;

    }


    private void selectRegularToppings(Sandwich sandwich) {
        boolean done = false;
        while (!done) {
            System.out.println(MenuPrompts.regularToppings);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                done = true;
            } else {
                RegularToppings topping = switch (choice) {
                    case 1 -> RegularToppings.LETTUCE;
                    case 2 -> RegularToppings.PEPPERS;
                    case 3 -> RegularToppings.ONIONS;
                    case 4 -> RegularToppings.TOMATOES;
                    case 5 -> RegularToppings.JALAPENOS;
                    case 6 -> RegularToppings.CUCUMBERS;
                    case 7 -> RegularToppings.PICKLES;
                    case 8 -> RegularToppings.GUACAMOLE;
                    case 9 -> RegularToppings.MUSHROOMS;
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        yield null;
                    }
                };
                if (topping != null) {
                    sandwich.addRegularTopping(topping);
                }
            }
        }
    }


    private BreadType selectBreadType() {
        BreadType breadType;
        System.out.println("Enter Bread Type (1: White, 2: Wheat, 3: Rye): ");
        int breadChoice = Integer.parseInt(scanner.nextLine());
        return switch (breadChoice) {
            case 1 -> BreadType.WHITE;
            case 2 -> BreadType.WHEAT;
            case 3 -> BreadType.RYE;
            default -> {
                System.out.println("Invalid choice. Please try again.");
                yield null;
            }
        };
    }
    private SandwichSize selectSandwichSize() {
        System.out.println("Enter Sandwich Size (1: Small, 2: Medium, 3: Large): ");
        int sizeChoice = Integer.parseInt(scanner.nextLine());
        return switch (sizeChoice) {
            case 1 -> SandwichSize.SMALL;
            case 2 -> SandwichSize.MEDIUM;
            case 3 -> SandwichSize.LARGE;
            default -> {
                System.out.println("Invalid choice. Please try again.");
                yield null;
            }
        };
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