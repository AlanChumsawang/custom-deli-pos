package com.pluralsight.deli.service.impl;

import com.pluralsight.deli.controller.MenuPrompts;
import com.pluralsight.deli.model.Drink;
import com.pluralsight.deli.model.enums.DrinkSize;
import com.pluralsight.deli.service.interfaces.DrinkService;

import java.util.Scanner;

public class DrinkServiceImpl implements DrinkService {
    private final Scanner scanner;
    private DrinkSize drinkSize;

    public DrinkServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Drink selectDrink() {
        System.out.print("\n\n\n\n\n\n" + MenuPrompts.getDrinks());
        String choice = scanner.nextLine();
        String drinkName;
        switch (choice) {
            case "1":
                drinkName = "Coke";
                break;
            case "2":
                drinkName = "Tea";
                break;
            case "3":
                drinkName = "Sprite";
                break;
            case "4":
                drinkName = "Fanta";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return null;
        }
        System.out.println("Choose a size (1: Small, 2: Medium, 3: Large): ");
        String sizeChoice = scanner.nextLine();
        switch (sizeChoice) {
            case "1":
                drinkSize = DrinkSize.SMALL;
                break;
            case "2":
                drinkSize = DrinkSize.MEDIUM;
                break;
            case "3":
                drinkSize = DrinkSize.LARGE;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return null;
        }
        return new Drink(drinkName, drinkSize);
    }
}