# PayNest Merchant Order Desk and Unified Checkout

## About

This Java application was built for the PayNest Merchant Order Desk (Capstone 1) and extended with unified checkout for Card, EFT, and Wallet payments (Capstone 3).

The application allows a merchant to:

- Create customers, products, and orders.
- Add products to orders and calculate order totals.
- Display order summaries.
- Validate customer, product, order, and payment information.
- Check out an order using Card, EFT, or Wallet.

## Technologies

- Java 21
- Maven
- JUnit 5
- VS Code

## Project Structure

```text
src
├── main/java/com/paynestsystem
│   ├── app
│   ├── domain
│   ├── payment
│   ├── presentation
│   └── service
└── test/java/com/paynestsystem
    ├── domain
    └── payment
```

### Main Classes

- `Customer` stores customer information.
- `Product` stores product information and price.
- `OrderItem` associates a product with a quantity and calculates the line total.
- `Order` manages order items, calculates the order total, and handles checkout.
- `OrderService` creates orders.
- `OrderSummaryPrinter` formats the order summary.
- `PayNestApplication` runs the checkout demo.

### Payment Classes

- `PaymentMethod` defines `boolean processPayment(double amount)` and `String getPaymentType()`.
- `CardPayment` validates card details and simulates a card payment.
- `EftPayment` simulates an EFT payment.
- `WalletPayment` checks the available balance and deducts the charge when funds are sufficient.
- `PaymentProcessor` calls the selected payment method, prints the result, and returns whether the payment succeeded.

## Payment Design

`Order.checkout(PaymentMethod)` calculates the order total as a `BigDecimal` and converts it to `double` when passing it to `PaymentProcessor`. The processor calls the selected implementation through the `PaymentMethod` interface. This keeps checkout logic in one place while each payment class handles its own rules. Adding a payment method, such as `BuyNowPayLaterPayment`, requires implementing the interface and selecting that implementation in the application.

## Payment Validation

- Checkout requires a payment method and a finite amount greater than zero.
- Card payment requires exactly 16 digits after removing spaces, an expiry of the current month or later, and a three-digit CVV.
- The `WalletPayment` constructor accepts a starting balance of zero or more and throws an `IllegalArgumentException` for a `null` or negative starting balance.
- Wallet payment stores the balance as a `BigDecimal` and converts the charge using `BigDecimal.valueOf(amount)`. If the charge exceeds the balance, checkout returns `false` and the wallet retains its full balance. If funds are sufficient, the wallet deducts the charge.
- Payments are simulated. Use dummy card details for testing and demonstration.

## Run the Project

From the project root, run the demo:

```powershell
mvn clean compile exec:java "-Dexec.mainClass=com.paynestsystem.app.PayNestApplication"
```

The demo prints the summary for Order 101, pays the R12,700.00 total from a wallet with a R13,000.00 starting balance, and prints `Order 101 completed.`

## Run the Tests

```powershell
mvn test
```

The tests cover successful checkout with Card, EFT, and Wallet, verify that checkout passes the calculated total to the payment method, and check payment failures and invalid inputs. The wallet decline test confirms that a R500 wallet refuses a R600 order and retains its R500 balance.

```text
Tests run: 36, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Student

Chuma Baba Gqodi
