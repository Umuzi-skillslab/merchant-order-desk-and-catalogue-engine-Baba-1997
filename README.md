# Merchant Order Desk

## Project Overview

This project was developed as part of the PayNest Merchant Order Desk Capstone. It is a Java 21 Maven application that demonstrates object-oriented programming concepts by managing customers, products, orders, and order totals.

The application allows a merchant to:

- Create products
- Create a customer
- Add products to an order with quantities
- Calculate line totals
- Calculate the grand total
- Print an order summary to the console

---

## Technologies Used

- Java 21
- Apache Maven
- JUnit 5

## Project Structure

````text
merchant-order-desk-maven
├── README.md
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── paynestsystem
│   │               ├── app
│   │               │   └── PayNestApplication.java
│   │               └── domain
│   │                   ├── Customer.java
│   │                   ├── Order.java
│   │                   ├── OrderItem.java
│   │                   └── Product.java
│   └── test
│       └── java
│           └── com
│               └── paynestsystem
│                   └── domain
│                       └── OrderItemTest.java

## How to compile

Open a terminal in the project folder and run:

```bash
mvn clean compile
````

Expected result:

```
BUILD SUCCESS
```

---

## How to Run the Application

Run the following command from the project directory:

```bash
mvn exec:java "-Dexec.mainClass=com.paynestsystem.app.PayNestApplication"
```

Example output:

```
Order ID: 101
Customer: Chuma Gqodi
Laptop x 1 = R12000.0
Wireless Mouse x 2 = R700.0
Grand Total: R12700.0
```

---

## How to Run Tests

Run the JUnit tests using:

```bash
mvn test
```

Expected result:
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

```
BUILD SUCCESS
```

---

## Classes

- **Product** – Represents a product with an ID, name, and price.
- **Customer** – Represents customer information.
- **OrderItem** – Represents a product and quantity within an order.
- **Order** – Manages customer orders and calculates totals.
- **PayNestApplication** – Entry point of the application that demonstrates the system.

---

## Author

Chuma Baba Gqodi
