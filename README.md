# Deli Order System

- [1: Overview](#1-overview)
- [2: Features](#2-features)
- [3: User Stories](#3-user-stories)
- [3.1: Customer Orders Signature Sandwich](#31-customer-orders-signature-sandwich)
- [3.2: Customer Orders Custom Sandwich](#32-customer-orders-custom-sandwich)
- [3.3: Customer Orders Drink](#33-customer-orders-drink)
- [3.4: Customer Orders Chips](#34-customer-orders-chips)
- [3.5: Customer Orders Multiple Items](#35-customer-orders-multiple-items)
- [4: Lessons Learned](#4-lessons-learned)


## 1: Overview
The Custom Deli POS project is a project that allows deli customers to order fully customized sandwiches, all while 
practicing Object-Oriented Programming (OOP) principles. The project exemplifies best practices in OOP concepts such as 
Separation of Concerns (SOC), DRY (Don’t Repeat Yourself), Abstraction, Inheritance, and Effective Git Branching 
Strategies. These practices create a scalable, robust system than can be easily updated as needed.

## UML Diagram
![UML diagram](uml/UML_Class_Diagram.png)

## 2: Features
- **Create Custom Sandwiches**: Users can build their own sandwiches by choosing from a variety of bread types, sizes, and toppings. The supports both regular and premium toppings, allowing for a completely customizable sandwich experience.
- **Add Chips and Drinks**: Users can add Chips and Drinks to order for an additional charge.
- **Generate and Save Receipts**: After a user chooses to check out a receipt is generated from their created order
- **Load and Save Order History**: There is a csv names transaction history that stores all transaction. This can be used in future updates to recall old transactions on admin servers

## 3: User Stories

### 3.1: Customer Orders Signature Sandwich
Title: Customer Orders Signature Sandwich
As a deli customer, I want to order a signature sandwich so that I can get a good deal and not make too many decisions.
Scenario:
```
- Given the customer is at the deli order system
- When the customer selects a signature sandwich
- Then the system should add the sandwich to the order with the correct price

```
Acceptance Criteria:
```
- The system should generate a receipt with the correct total
- The system should have two signature sandwiches: "BLT" and "Philly Cheese"
- The signature sandwiches should have predefined toppings and prices
- The customer should be able to select a signature sandwich
- The selected sandwich should be visable in the order summary
- The checkout process should include the signature sandwich with the correct price 
```

### 3.2: Customer Orders Custom Sandwich
Title: Customer Orders Custom Sandwich
As a deli customer, I want to create a custom sandwich so that I can choose exactly what I want.
Scenario:
```
- Given the customer is at the deli order system
- When the customer selects add sandwich
- Then they can choose to create a fully custom sandwich

```
Acceptance Criteria:
```
- The system should prompt the customer if they want a signature sandwich
- The system should prompt the customer to select a sandwich size
- The system should prompt the customer to select a bread type
- The system should ask the customer if they want the sandwich toasted
- The system should prompt the customer to select Premium toppings
- The system should prompt the customer to select Regular toppings
- The system should prompt the customer to select sauce
- The system should ask the customer if they want extra meat
- The systems returns to order screen and displays the custom sandwich in the order summary
- When the customer checks out
- Then the system should generate a receipt with the correct total
```

### 3.3: Customer Orders Drink
Title: Customer Orders Drink
As a deli customer, I want to order a drink because I forgot to order one with my sandwich
Scenario:
```
- Given the customer is at the deli order system
- When the customer selects add drink
- Then the system should add the drink to the order with the correct price
```
Acceptance Criteria:
```
- The system should prompt the customer to select a drink size
- The system should prompt the customer to select a drink type
- The system should add the drink to the order summary
- The system should generate a receipt with the correct total
```
### 3.4: Customer Orders Chips
Title: Customer Orders Chips
As a deli customer, I want to order chips because I am still hungry after my sandwich
Scenario:
```
- Given the customer is at the deli order system
- When the customer selects add chips
- Then the system should add the chips to the order with the correct price
```
Acceptance Criteria:
```
- The system should prompt the customer to select a chip type
- The system should add the chips to the order summary
- The system should generate a receipt with the correct total
```

### 3.5: Customer Orders Multiple Items
Title: Customer Orders Multiple Items
As a deli customer, I want to order multiple items because I am very hungry
Scenario:
```
- Given the customer is at the deli order system
- When the customer selects multiple items
- Then the system should add all items to the order with the correct prices
```
Acceptance Criteria:
```
- The system should prompt the customer to select multiple items
- The system should add all items to the order summary
- The system should generate a receipt with the correct total
```


## 4: Lessons Learned
During the development of the Deli Order System, several advanced Object-Oriented Programming (OOP) concepts were applied and my understanding of these concepts improved. Here are some key takeaways from the project:

### Inheritance
Inheritance was used to create a hierarchy of classes, allowing for code reuse and a clear structure. For example, the 
`Product` class serves as a base class for specific product types like `Sandwich`, `Drink`, and `Chips`.
```
public class Sandwich extends Product {
    protected SandwichSize size;
    protected BreadType breadType;
    protected List<RegularToppings> regularToppings;
    protected List<PremiumToppings> premiumToppings;
    protected List<Sauces> sauces;
    protected boolean isToasted;
    protected boolean extraMeat;

    public Sandwich(String name, SandwichSize size, BreadType breadType,
                    boolean isToasted) {
        super(name, 0);
```

### Overriding Methods
Method overriding was utilized to provide specific implementations of methods in subclasses. For instance, the 
`calculateProductTotal` and `productDetails` methods in the `Sandwich`, `Drink`, and `Chips` classes override the 
abstract methods defined in the `Product` class.
```
@Override
    public double calculateProductTotal() {
        if (size == DrinkSize.SMALL) {
            return 2.00;
        } else if (size == DrinkSize.MEDIUM) {
            return 2.50;
        } else if (size == DrinkSize.LARGE) {
            return 3.00;
        }
        return 0;
```

### Overloading Constructors
Constructor overloading was utilized to provide multiple ways to instantiate objects. The `Sandwich` class, 
for example, has multiple constructors to account for different scenarios, such as creating a custom 
sandwich or a signature sandwich with predefined toppings.
```
 public Sandwich(String name, SandwichSize size, BreadType breadType,
                    boolean isToasted) {
        super(name, 0);
        this.size = size;
        this.breadType = breadType;
        this.regularToppings = new ArrayList<>();
        this.premiumToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.isToasted = isToasted;
        this.extraMeat = false;

        if (size == SandwichSize.SMALL) {
            this.startingPrice = 5.50;
        } else if (size == SandwichSize.MEDIUM) {
            this.startingPrice = 7.00;
        } else if (size == SandwichSize.LARGE) {
            this.startingPrice = 8.50;
        }
    }

    public Sandwich(String name, SandwichSize size, BreadType breadType,
                    boolean isToasted, List<RegularToppings> regularToppings, List<PremiumToppings> premiumToppings, List<Sauces> sauces) {
        super(name, 0);
        this.size = size;
        this.breadType = breadType;
        this.regularToppings = regularToppings;
        this.premiumToppings = premiumToppings;
        this.isToasted = isToasted;
        this.sauces = sauces;
        this.extraMeat = false;
```
These concepts helped in creating a flexible, maintainable, and scalable codebase for the Deli Order System.

### Effective Git Branching Strategies
I utilized the main branch only for stable code and created dev and feature branches for development.
This helped me to work on new features without affecting the main branch

### Separation of Concerns (SOC)
In the code below I emphasized the separation of concerns I made sure that the UI only handled user input and 
all business logic was handled in service classes. This made the codebase easier to understand and maintain.
```
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
```
### String Formatting
The code below shows how I used string formatting to create a visually appealing order summary. I faced challenges
with making the Order summary box append equally on both sides. I used the `%-43s` to format the string to be 43 
characters long and left justified.I also faced challenges
in making it get bigger as the order got bigger. I used the `StringBuilder` class to append the order summary each time and 
return it as a string. This helped me to create a visually appealing order summary.
```
@Override
    public String orderFormatter(Order order) {
        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("╔════════════════════════════════════════════════════╗\n");
        orderSummary.append("║                    Order Summary                   ║\n");
        orderSummary.append("╠════════════════════════════════════════════════════╣\n");
        for (Product item : order.getItems()) {
            String itemName = item.getName();
            String itemPrice = String.format("$%.2f", item.calculateProductTotal());
            orderSummary.append(String.format("║ %-43s %6s ║\n", itemName, itemPrice));
        }
        orderSummary.append("╠════════════════════════════════════════════════════╣\n");
        double total = calculateTotal(order);
        orderSummary.append(String.format("║ %-43s %6s ║\n", "Total:", String.format("$%.2f", total)));
        orderSummary.append("╚════════════════════════════════════════════════════╝\n");
        return orderSummary.toString();
    }
```
### Interface Segregation Principle(ISP)
The Interface Segregation Principle is a design principle that states that a class should not be forced to implement an 
interface that it does not use. For example related to the deli a sandwich service should not have to implement a drink interface
I used the Interface Segregation Principle to create interfaces that were specific to the classes that implemented them.
```
public interface SandwichService {
    Sandwich createSandwich();
```
For example, the `SandwichService` interface has a method `createSandwich` that is specific to the `Sandwich` class.
```
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
        Sandwich sandwich = new Sandwich("Custom Sandwich", size, breadType, isToasted);
        selectPremiumToppings(sandwich);
        selectRegularToppings(sandwich);
        selectSauces(sandwich);
        extraMeat(sandwich);
        return sandwich;
    }
```
Along with following ISP best practices, I also made sure to follow the Single Responsibility Principle (SRP) by ensuring
that each class had a sole responsibility and that all of its services were aligned with that responsibility.
This class for example only handles sandwich creation and nothing else. While also utilizing private methods to
break down the creation of a sandwich into smaller more manageable methods.
