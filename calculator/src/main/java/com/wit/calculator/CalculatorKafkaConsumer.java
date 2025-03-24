package com.wit.calculator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorKafkaConsumer {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "calculator-topic", groupId = "calculator-group")
    public void listen(String message) {
        try {
            // Converter mensagem JSON para objeto
            JsonNode jsonNode = objectMapper.readTree(message);
            String operation = jsonNode.get("operation").asText();
            BigDecimal a = new BigDecimal(jsonNode.get("a").asText());
            BigDecimal b = new BigDecimal(jsonNode.get("b").asText());

            BigDecimal result;
            switch (operation) {
                case "sum":
                    result = calculatorService.sum(a, b);
                    break;
                case "subtract":
                    result = calculatorService.subtract(a, b);
                    break;
                case "multiply":
                    result = calculatorService.multiply(a, b);
                    break;
                case "divide":
                    result = calculatorService.divide(a, b);
                    break;
                default:
                    throw new IllegalArgumentException("Operação inválida: " + operation);
            }

            // Criar JSON de resposta
            String resultMessage = String.format("{\"operation\": \"%s\", \"a\": %s, \"b\": %s, \"result\": %s}",
                    operation, a, b, result);

            // Publicar resultado no Kafka
            kafkaTemplate.send("calculator-result-topic", resultMessage);
            System.out.println("Resultado enviado: " + resultMessage);

        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}
