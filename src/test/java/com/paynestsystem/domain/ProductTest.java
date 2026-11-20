package com.paynestsystem.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {


@Test
void shouldCreateProductWithValidDetails() {
    Product product = new Product(
            1,
            "Laptop",
            new BigDecimal("10000.00")
    );

    assertEquals(1, product.getId());
    assertEquals("Laptop", product.getName());
    assertEquals(
            new BigDecimal("10000.00"),
            product.getPrice()
    );
}

@Test
void shouldRejectZeroProductId() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Product(
                    0,
                    "Laptop",
                    new BigDecimal("10000.00")
            )
    );
}

@Test
void shouldRejectNegativeProductId() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Product(
                    -1,
                    "Laptop",
                    new BigDecimal("10000.00")
            )
    );
}

@Test
void shouldRejectNullProductName() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Product(
                    1,
                    null,
                    new BigDecimal("10000.00")
            )
    );
}

@Test
void shouldRejectBlankProductName() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Product(
                    1,
                    "   ",
                    new BigDecimal("10000.00")
            )
    );
}

@Test
void shouldRejectNullProductPrice() {
    assertThrows(
            NullPointerException.class,
            () -> new Product(
                    1,
                    "Laptop",
                    null
            )
    );
}

@Test
void shouldRejectNegativeProductPrice() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Product(
                    1,
                    "Laptop",
                    new BigDecimal("-1.00")
            )
    );
}

@Test
void shouldAllowZeroProductPrice() {
    Product product = new Product(
            1,
            "Free Item",
            BigDecimal.ZERO
    );

    assertEquals(BigDecimal.ZERO, product.getPrice());
}


}
