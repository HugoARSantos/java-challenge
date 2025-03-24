package com.wit.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"calculator-topic", "calculator-result-topic"})
@EnableKafka
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CalculatorKafkaConsumerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void testListen() throws InterruptedException {
        // Enviar mensagem para o tópico
        String testMessage = "{\"operation\": \"sum\", \"a\": \"2.5\", \"b\": \"3.5\"}";
        kafkaTemplate.send("calculator-topic", testMessage);

        // Esperar um pouco para garantir que a mensagem foi processada
        Thread.sleep(2000);  // Tempo para o listener processar a mensagem
    }
}
