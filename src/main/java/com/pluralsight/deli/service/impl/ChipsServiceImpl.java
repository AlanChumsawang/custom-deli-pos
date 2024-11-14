package com.pluralsight.deli.service.impl;

import com.pluralsight.deli.service.interfaces.ChipService;
import com.pluralsight.deli.controller.MenuPrompts;
import com.pluralsight.deli.model.Chips;

import java.util.Scanner;

public class ChipsServiceImpl implements ChipService {
    private final Scanner scanner;

    public ChipsServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Chips selectChips() {
        System.out.print("\n\n\n\n\n\n" + MenuPrompts.getChips());
        String choice = scanner.nextLine();
        String chipsName;
        switch (choice) {
            case "1":
                chipsName = "Lays Original";
                break;
            case "2":
                chipsName = "Lays BBQ";
                break;
            case "3":
                chipsName = "Lays Sour Cream & Onion";
                break;
            case "4":
                chipsName = "Doritos Nacho Cheese";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return null;
        }
        return new Chips(chipsName);
    }
}