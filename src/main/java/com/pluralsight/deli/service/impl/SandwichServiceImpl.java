package com.pluralsight.deli.service.impl;

import com.pluralsight.deli.controller.MenuPrompts;
import com.pluralsight.deli.model.Sandwich;
import com.pluralsight.deli.model.enums.BreadType;
import com.pluralsight.deli.model.enums.PremiumToppings;
import com.pluralsight.deli.model.enums.RegularToppings;
import com.pluralsight.deli.model.enums.SandwichSize;
import com.pluralsight.deli.service.interfaces.SandwichService;

import java.util.Scanner;

public class SandwichServiceImpl implements SandwichService {
    private final Scanner scanner;

    public SandwichServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Sandwich createSandwich(){
        boolean isSignature = isSignature();
        SandwichSize size = selectSandwichSize();
        BreadType breadType = selectBreadType();
        boolean isToasted = isToasted();
        Sandwich sandwich = new Sandwich("Custom Sandwich", size,breadType, isToasted, isSignature);
        selectPremiumToppings(sandwich);
        selectRegularToppings(sandwich);
        return sandwich;
    }

    @Override
    public boolean isSignature() {
        System.out.println("Would you like to order a signature sandwich? (1: Yes, 2: No): ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice == 1;
    }

    @Override
    public SandwichSize selectSandwichSize() {
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

    @Override
    public boolean isToasted() {
        System.out.println("Would you like your sandwich toasted? (1: Yes, 2: No): ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice == 1;
    }

    @Override
    public BreadType selectBreadType() {
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

    @Override
    public void selectRegularToppings(Sandwich sandwich) {
        boolean done = false;
        while (!done) {
            System.out.println(MenuPrompts.getRegularToppings());
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

    @Override
    public void selectPremiumToppings(Sandwich sandwich) {
        boolean done = false;
        while (!done) {
            System.out.println(MenuPrompts.getPremiumToppings());
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
    }
}

