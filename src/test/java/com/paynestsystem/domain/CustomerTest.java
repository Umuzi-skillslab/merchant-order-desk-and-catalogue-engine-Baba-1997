package com.paynestsystem.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {


@Test
void shouldCreateCustomerWithValidDetails() {
    Customer customer = new Customer(1, "John Doe", "john@example.com");

    assertEquals(1, customer.getId());
    assertEquals("John Doe", customer.getName());
    assertEquals("john@example.com", customer.getEmail());
}

@Test
void shouldRejectCustomerIdOfZero() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Customer(0, "John Doe", "john@example.com")
    );
}

@Test
void shouldRejectNegativeCustomerId() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Customer(-1, "John Doe", "john@example.com")
    );
}

@Test
void shouldRejectNullCustomerName() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Customer(1, null, "john@example.com")
    );
}

@Test
void shouldRejectBlankCustomerName() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Customer(1, "   ", "john@example.com")
    );
}

@Test
void shouldRejectNullCustomerEmail() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Customer(1, "John Doe", null)
    );
}

@Test
void shouldRejectBlankCustomerEmail() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Customer(1, "John Doe", "   ")
    );
}


}
