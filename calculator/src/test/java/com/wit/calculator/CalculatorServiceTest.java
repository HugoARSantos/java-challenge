package com.wit.calculator;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    // Função para comparar BigDecimal com tolerância (delta)
    private void assertBigDecimalEquals(BigDecimal expected, BigDecimal actual, BigDecimal delta) {
        assertTrue(expected.subtract(actual).abs().compareTo(delta) <= 0, 
            "Expected: " + expected + " but got: " + actual);
    }

    @Test
    public void testAddition() {
        BigDecimal result = calculatorService.sum(new BigDecimal("2.5"), new BigDecimal("3.5"));
        assertBigDecimalEquals(new BigDecimal("6.0"), result, new BigDecimal("0.0001"));
    }

    @Test
    public void testSubtraction() {
        BigDecimal result = calculatorService.subtract(new BigDecimal("5.5"), new BigDecimal("3.0"));
        assertBigDecimalEquals(new BigDecimal("2.5"), result, new BigDecimal("0.0001"));
    }

    @Test
    public void testMultiplication() {
        BigDecimal result = calculatorService.multiply(new BigDecimal("2.0"), new BigDecimal("3.0"));
        assertBigDecimalEquals(new BigDecimal("6.0"), result, new BigDecimal("0.0001"));
    }

    @Test
    public void testDivision() {
        BigDecimal result = calculatorService.divide(new BigDecimal("6.0"), new BigDecimal("3.0"));
        assertBigDecimalEquals(new BigDecimal("2.0"), result, new BigDecimal("0.0001"));
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(new BigDecimal("6.0"), BigDecimal.ZERO));
    }
}
