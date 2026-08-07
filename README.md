# Merchant Order Desk

## About

This is a Java project built for the PayNest Merchant Order Desk Capstone.

The application allows a merchant to:

- Create customers and products
- Create orders
- Add products to orders
- Calculate order totals
- Display an order summary
- Validate incorrect information

## Technologies

- Java 21
- Maven
- JUnit 5
- Vs Code

## Project Structure

```text
src
├── main
│   └── java
│       └── com.paynestsystem
│           ├── app
│           ├── domain
│           ├── presentation
│           └── service
│
└── test
    └── java
        └── com.paynestsystem
            └── domain
```

### Main Classes

- `Customer` – stores customer information.
- `Product` – stores product information and price.
- `OrderItem` – connects a product to a quantity.
- `Order` – manages order items and calculates totals.
- `OrderService` – creates orders.
- `OrderSummaryPrinter` – displays the order summary.
- `PayNestApplication` – runs the application.

## Improvements

The project uses `BigDecimal` for money calculations and separates presentation from the domain logic.

The main domain objects use `final` fields and no setters where possible to make them more immutable.

## Run the Project

Compile:

```bash
mvn clean compile
```

Run:

```bash
mvn exec:java "-Dexec.mainClass=com.paynestsystem.app.PayNestApplication"
```

Run tests:

```bash
mvn clean test
```

### Test Result

```text
Tests run: 27
Failures: 0
Errors: 0
BUILD SUCCESS
```

## Student

Chuma Baba Gqodi
