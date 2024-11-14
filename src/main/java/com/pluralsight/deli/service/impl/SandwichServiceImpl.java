package com.pluralsight.deli.service.impl;

import com.pluralsight.deli.controller.MenuPrompts;
import com.pluralsight.deli.model.Sandwich;
import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.service.interfaces.SandwichService;

import java.util.List;
import java.util.Scanner;

public class SandwichServiceImpl implements SandwichService {
    private final Scanner scanner;

    public SandwichServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Sandwich createSandwich() {
        boolean isSignature = isSignature();
        if (isSignature) {
            return selectSignature();
        }
        SandwichSize size = selectSandwichSize();
        BreadType breadType = selectBreadType();
        boolean isToasted = isToasted();
        Sandwich sandwich = new Sandwich("Custom Sandwich", size, breadType, isToasted, false);
        selectPremiumToppings(sandwich);
        selectRegularToppings(sandwich);
        selectSauces(sandwich);
        return sandwich;
    }


    private boolean isSignature() {
        System.out.println("Would you like to order a signature sandwich? (1: Yes, 2: No): ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice == 1;
    }


    private Sandwich selectSignature() {
        System.out.println("Enter Signature Sandwich (1: BLT, 2: Philly Cheese): ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                List<PremiumToppings> premiumToppings = List.of(PremiumToppings.BACON, PremiumToppings.CHEDDAR_CHEESE);
                List<RegularToppings> regularToppings = List.of(RegularToppings.LETTUCE, RegularToppings.TOMATOES);
                List<Sauces> sauces = List.of(Sauces.RANCH);
                return new Sandwich("BLT", SandwichSize.MEDIUM, BreadType.WHITE, true, true, regularToppings, premiumToppings, sauces);
            case 2:
                List<PremiumToppings> premToppings = List.of(PremiumToppings.STEAK, PremiumToppings.AMERICAN_CHEESE);
                List<RegularToppings> regToppings = List.of(RegularToppings.PEPPERS);
                List<Sauces> sauceList = List.of(Sauces.MAYO);
                return new Sandwich("Philly Cheese", SandwichSize.MEDIUM, BreadType.WHITE, true, true, regToppings, premToppings, sauceList);
        }
        return null;
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


    private boolean isToasted() {
        System.out.println("Would you like your sandwich toasted? (1: Yes, 2: No): ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice == 1;
    }


    private BreadType selectBreadType() {
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


    private void selectRegularToppings(Sandwich sandwich) {
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


    private void selectPremiumToppings(Sandwich sandwich) {
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

    private void selectSauces(Sandwich sandwich) {
        boolean done = false;
        while (!done) {
            System.out.println(MenuPrompts.getSauces());
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                done = true;
            } else {
                Sauces sauce = switch (choice) {
                    case 1 -> Sauces.MAYO;
                    case 2 -> Sauces.MUSTARD;
                    case 3 -> Sauces.KETCHUP;
                    case 4 -> Sauces.RANCH;
                    case 5 -> Sauces.THOUSAND_ISLAND;
                    case 6 -> Sauces.VINAIGRETTE;
                    case 7 -> Sauces.AU_JUS;
                    case 0 -> null;
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        yield null;
                    }
                };
                if (sauce != null) {
                    sandwich.addSauce(sauce);
                }
            }
        }
    }
}

