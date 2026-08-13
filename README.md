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
- Check out an order using different payment methods (Card, EFT, Wallet)

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
│           ├── payment
│           ├── presentation
│           └── service
│
└── test
    └── java
        └── com.paynestsystem
            ├── domain
            └── payment
```

### Main Classes

- `Customer` - stores customer information.
- `Product` - stores product information and price.
- `OrderItem` - connects a product to a quantity.
- `Order` - manages order items, calculates totals, and handles checkout.
- `OrderService` - creates orders.
- `OrderSummaryPrinter` - displays the order summary.
- `PayNestApplication` - runs the application.

### Payment Classes

- `PaymentMethod` - interface defining the payment contract (`processPayment`, `getPaymentType`).
- `CardPayment` - processes a payment via card.
- `EftPayment` - processes a payment via EFT.
- `WalletPayment` - processes a payment via wallet.
- `PaymentProcessor` - runs a payment against any `PaymentMethod` and reports success/failure.

## Payment Design

Payments are built around polymorphism rather than branching on payment type.

`PaymentMethod` is an interface with two methods: `processPayment(BigDecimal amount)` and
`getPaymentType()`. `CardPayment`, `EftPayment`, and `WalletPayment` each implement this
interface independently, providing their own behaviour for each rail.

`PaymentProcessor` and `Order.checkout(PaymentMethod paymentMethod)` both depend only on the
`PaymentMethod` interface, never on a specific payment class. This means there is no
`if`/`else` or `switch` statement anywhere deciding which payment type is being used — Java
resolves the correct implementation automatically at runtime (dynamic dispatch), based on the
actual object passed in.

The benefit: adding a new payment rail (for example, a future `BuyNowPayLaterPayment`) only
requires creating one new class that implements `PaymentMethod`. No existing code in
`PaymentProcessor` or `Order` needs to change.

`Order.checkout()` reuses the existing `calculateTotal()` method from the order's line items,
converts nothing, and passes that real `BigDecimal` total directly into `PaymentProcessor` —
the amount used for payment is always the order's actual total, never a hardcoded value.

## Run the Project

Compile:

```bash
mvn clean compile
```

Run:

```bash
mvn compile exec:java "-Dexec.mainClass=com.paynestsystem.app.PayNestApplication"
```

Run tests:

```bash
mvn clean test
```

### Test Result

```text
Tests run: 29
Failures: 0
Errors: 0
BUILD SUCCESS
```

## Student

Chuma Baba Gqodi
