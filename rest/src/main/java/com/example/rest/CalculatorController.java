 
package com.example.rest;

import com.example.calculator.CalculatorService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService = new CalculatorService();

    @GetMapping("/sum")
    public Map<String, BigDecimal> sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return Map.of("result", calculatorService.add(a, b));
    }

    @GetMapping("/subtract")
    public Map<String, BigDecimal> subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return Map.of("result", calculatorService.subtract(a, b));
    }

    @GetMapping("/multiply")
    public Map<String, BigDecimal> multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return Map.of("result", calculatorService.multiply(a, b));
    }

    @GetMapping("/divide")
    public Map<String, BigDecimal> divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return Map.of("result", calculatorService.divide(a, b));
    }
}