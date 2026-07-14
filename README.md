# Merchant Order Desk

## Project Overview

This project was developed as part of the PayNest Merchant Order Desk Capstone.

The application allows a merchant to create products, create a customer, add products to an order, calculate the total cost of the order, and print an order summary to the console.

The project demonstrates object-oriented programming using Java and follows a Maven project structure.

---

## Technologies Used

- Java 21
- Apache Maven
- JUnit 5

---

## Project Structure

```text
merchant-order-desk-maven
│
├── README.md
├── pom.xml
│
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── paynestsystem
    │               ├── app
    │               │   └── PayNestApplication.java
    │               │
    │               ├── domain
    │               │   ├── Customer.java
    │               │   ├── Order.java
    │               │   ├── OrderItem.java
    │               │   └── Product.java
    │               │
    │               └── service
    │                   └── OrderService.java
    │
    └── test
        └── java
            └── com
                └── paynestsystem
                    └── domain
                        ├── OrderItemTest.java
                        └── OrderTest.java
```

---

## How to Compile

Open a terminal in the project folder and run:

```bash
mvn clean compile
```

Expected output:

```text
BUILD SUCCESS
```

---

## How to Run the Application

Run the following command:

```bash
mvn exec:java "-Dexec.mainClass=com.paynestsystem.app.PayNestApplication"
```

Example output:

```text
Order ID: 101
Customer: Chuma Gqodi
Laptop x 1 = R12000.0
Wireless Mouse x 2 = R700.0
Grand Total: R12700.0
```

---

## How to Run Tests

Run:

```bash
mvn test
```

Expected output:

```text
Tests run: 6
Failures: 0
Errors: 0
BUILD SUCCESS
```

---

## Classes

### Product

Stores the product ID, name, and price.

### Customer

Stores the customer ID, name, and email address.

### OrderItem

Stores a product and its quantity. It also calculates the total price for that product.

### Order

Stores customer orders, adds products to the order, calculates the grand total, and prints the order summary.

### OrderService

Creates new orders and separates order creation from the main application.

### PayNestApplication

The entry point of the application. It creates sample data and demonstrates how the application works.

---

## Validation

The application checks that the quantity entered for an order item is greater than zero.

If the quantity is zero or negative, an IllegalArgumentException is thrown to prevent invalid orders.

---

## System Flow

PayNestApplication
|
v
OrderService
|
v
Order
/ \
Customer OrderItem
|
v
Product

The PayNestApplication creates sample products and a customer. The OrderService creates the order. The Order stores the customer and order items, calculates the grand total, and prints the order summary. Each OrderItem connects a product to a quantity.

---

## Student

Chuma Baba Gqodi
