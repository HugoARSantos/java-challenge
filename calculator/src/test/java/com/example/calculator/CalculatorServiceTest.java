 
package com.example.calculator;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private final CalculatorService calculator = new CalculatorService();

    @Test
    void testAddition() {
        assertEquals(new BigDecimal("5"), calculator.add(new BigDecimal("2"), new BigDecimal("3")));
    }

    @Test
    void testSubtraction() {
        assertEquals(new BigDecimal("1"), calculator.subtract(new BigDecimal("3"), new BigDecimal("2")));
    }

    @Test
    void testMultiplication() {
        assertEquals(new BigDecimal("6"), calculator.multiply(new BigDecimal("2"), new BigDecimal("3")));
    }

    @Test
    void testDivision() {
        assertEquals(new BigDecimal("2.5000000000"), calculator.divide(new BigDecimal("5"), new BigDecimal("2")));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(new BigDecimal("5"), BigDecimal.ZERO));
    }
}