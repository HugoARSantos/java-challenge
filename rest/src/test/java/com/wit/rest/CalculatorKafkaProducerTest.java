package com.wit.rest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RestApplication.class)
@EmbeddedKafka(partitions = 1, topics = {"calculator-topic"})
@EnableKafka
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CalculatorKafkaProducerTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private CalculatorKafkaProducer kafkaProducer;

    @Test
    public void testSendMessage() throws Exception {
        // Criar a mensagem de teste
        BigDecimal a = new BigDecimal("10.5");
        BigDecimal b = new BigDecimal("2.5");
        String operation = "sum";
        
        // Enviar a mensagem via Producer
        kafkaProducer.sendMessage(a, b, operation);

        // Criar propriedades do consumidor para o grupo de teste
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("test-group", "true", embeddedKafkaBroker);

        // Criar o consumidor com as propriedades fornecidas
        DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);

        // Criar o consumidor
        var consumer = consumerFactory.createConsumer();
        embeddedKafkaBroker.consumeFromEmbeddedTopics(consumer, "calculator-topic");

        ConsumerRecord<String, String> received = KafkaTestUtils.getSingleRecord(consumer, "calculator-topic");

        assertNotNull(received);

        String expectedMessage = String.format("{\"a\":%s,\"b\":%s,\"operation\":\"%s\"}", a, b, operation);
        assertEquals(expectedMessage, received.value());

        System.out.println("Test - Mensagem Kafka enviada corretamente: " + received.value());
    }
}
