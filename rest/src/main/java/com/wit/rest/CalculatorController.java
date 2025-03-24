package com.wit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorKafkaProducer kafkaProducer;

    private String resultMessage;

    @PostMapping("/sum")
    public String sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.add(b);
        kafkaProducer.sendMessage(a, b, "sum");
        return "Resultado da soma: " + result;
    }

    @PostMapping("/subtract")
    public String subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.subtract(b); 
        kafkaProducer.sendMessage(a, b, "subtract");
        return "Resultado da subtração: " + result;
    }

    @PostMapping("/multiply")
    public String multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.multiply(b);
        kafkaProducer.sendMessage(a, b, "multiply");
        return "Resultado da multiplicação: " + result;
    }

    @PostMapping("/divide")
    public String divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "Erro: Não é possível dividir por zero";
        }
        BigDecimal result = a.divide(b, 2, RoundingMode.HALF_UP);
        kafkaProducer.sendMessage(a, b, "divide");
        return "Resultado da divisão: " + result;
    }

    @KafkaListener(topics = "calculator-result-topic", groupId = "rest-group")
    public void listenResult(String message) {
        this.resultMessage = message;
    }

    @GetMapping("/result")
    public String getResult() {
        if (resultMessage != null) {
            return "Resultado: " + resultMessage;
        } else {
            return "Aguardando resultado...";
        }
    }
}

