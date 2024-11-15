# Deli Order System

- [1: Overview](#1-overview)
- [2: Features](#2-features)
- [3: User Stories](#3-user-stories)
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

# 3: User Stories

## 4: Lessons Learned
During the development of the Deli Order System, several advanced Object-Oriented Programming (OOP) concepts were applied and reinforced:

### Inheritance
Inheritance was used to create a hierarchy of classes, allowing for code reuse and a clear structure. For example, the `Product` class serves as a base class for specific product types like `Sandwich`, `Drink`, and `Chips`.

### Overriding Methods
Method overriding was utilized to provide specific implementations of methods in subclasses. For instance, the `calculateProductTotal` and `productDetails` methods in the `Sandwich`, `Drink`, and `Chips` classes override the abstract methods defined in the `Product` class.

### Overloading Constructors
Constructor overloading was employed to provide multiple ways to instantiate objects. The `Sandwich` class, for example, has multiple constructors to accommodate different initialization scenarios, such as creating a custom sandwich or a signature sandwich with predefined toppings.

These concepts helped in creating a flexible, maintainable, and scalable codebase for the Deli Order System.