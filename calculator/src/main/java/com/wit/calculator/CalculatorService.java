package com.wit.calculator;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {

    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (b.equals(BigDecimal.ZERO)) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a.divide(b);
    }
}
