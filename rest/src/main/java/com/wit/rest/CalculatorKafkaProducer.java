package com.wit.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public CalculatorKafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper; // Agora está sendo utilizado
    }

    public void sendMessage(BigDecimal a, BigDecimal b, String operation) {
        try {
            // Criar um objeto de mensagem com a operação e valores
            Message message = new Message(a, b, operation);

            // Converter para JSON usando o ObjectMapper
            String messageJson = objectMapper.writeValueAsString(message);

            // Enviar para o Kafka
            kafkaTemplate.send("calculator-topic", messageJson);

            System.out.println("Mensagem JSON enviada: " + messageJson);
        } catch (Exception e) {
            System.err.println("Erro ao criar a mensagem JSON: " + e.getMessage());
        }
    }

    public static class Message {
        private final BigDecimal a;
        private final BigDecimal b;
        private final String operation;
    
        public Message(BigDecimal a, BigDecimal b, String operation) {
            this.a = a;
            this.b = b;
            this.operation = operation;
        }
    
        public BigDecimal getA() { return a; }
        public BigDecimal getB() { return b; }
        public String getOperation() { return operation; }
    }
}
