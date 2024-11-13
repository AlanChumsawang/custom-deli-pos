package com.pluralsight.deli.controller;

public class MenuPrompts {
     static String homeMenu = """
             Welcome to the Deli Order System
     1: New Order
     0: Exit
    \s
     Choose an option:\s""";

     static String orderMenu = """
             Order Menu
        1: Add Sandwich
        2: Add Chips
        3: Add Drink
        4: Checkout
        0: Cancel Order
             """;

     static String chips = """
             1: Lays Original
             2: Lays BBQ
             3: Lays Sour Cream & Onion
             4: Doritos Nacho Cheese
             5: Doritos Cool Ranch
            \s
            Choose an option: \s""";

     static String regularToppings = """
             1: Lettuce
             2: Peppers
             3: Onions
             4: Tomatoes
             5: Jalapenos
             6: Cucumbers
             7: Pickles
             8: Guacamole
             9: Mushrooms
             0: Done
             \s
             Choose an option: \s""";

     static String premiumToppings = """
             1: Steak
             2: Ham
             3: Salami
             4: Roast Beef
             5: Chicken
             6: Bacon
             7: American Cheese
             8: Swiss Cheese
             9: Provolone Cheese
             10: Cheddar Cheese
             0: Done
             \s
             Choose an option: \s""";

    public static String getHomeMenu() {
        return homeMenu;
    }

    public static String getOrderMenu() {
        return orderMenu;
    }
}
