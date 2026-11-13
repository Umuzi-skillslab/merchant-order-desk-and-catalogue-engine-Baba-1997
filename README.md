# PayNest Merchant Order Desk and Unified Checkout

## About

This Java application was built for the PayNest Merchant Order Desk (Capstone 1) and extended with unified checkout for card, EFT, and wallet payments (Capstone 3).

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
- VS Code

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

- `PaymentMethod` - defines `processPayment(BigDecimal amount)` and `getPaymentType()`.
- `CardPayment` - simulates a card payment and returns `"CARD"` as its payment label.
- `EftPayment` - simulates an EFT payment and returns `"EFT"` as its payment label.
- `WalletPayment` - simulates a wallet payment and returns `"WALLET"` as its payment label.
- `PaymentProcessor` - calls the selected payment method and reports whether the payment succeeded.

## Payment Design

Payments are built around polymorphism rather than branching on payment type.

`PaymentMethod` is an interface with two methods: `processPayment(BigDecimal amount)` and
`getPaymentType()`. `CardPayment`, `EftPayment`, and `WalletPayment` each implement this
interface independently, providing their own behaviour for each rail.

`PaymentProcessor.processPayment()` and `Order.checkout()` both accept a `PaymentMethod`. At runtime, Java calls the implementation for the payment object passed in, such as `CardPayment`, `EftPayment`, or `WalletPayment`.

The benefit: adding a new payment rail (for example, a future `BuyNowPayLaterPayment`) only
requires creating one new class that implements `PaymentMethod`. No existing code in
`PaymentProcessor` or `Order` needs to change.

`Order.checkout()` calls `calculateTotal()` to add up the order's line items, then passes the resulting `BigDecimal` total to `PaymentProcessor` as the payment amount.

## Payment Validation

- `Order.checkout()` requires a payment method. `PaymentProcessor` requires a payment amount greater than zero. These rules apply to Card, EFT, and Wallet.
- `CardPayment` requires a card number with exactly 16 digits after removing spaces, an expiry month of the current month or later, and three-digit CVV.
  -Payments are simulated. Use dummy card details for testing and demonstration.

## Run the Project

Compile:

```bash
mvn clean compile
```

Run:

```bash
mvn compile exec:java "-Dexec.mainClass=com.paynestsystem.app.PayNestApplication"
```

The demo displays order `101`, calculates a total of `R12 700,00`, and completes the order after a successful simulated Wallet payment.

Run tests:

```bash
mvn clean test
```

### Test Result

Run `mvn test` from the project root.All 35 tests passed, including test for a missing payment method, a zero-totalorder, a short card number, an expired card, and an invalid CVV.

```text
Tests run: 35
Failures: 0
Errors: 0
BUILD SUCCESS
```

## Student

Chuma Baba Gqodi
