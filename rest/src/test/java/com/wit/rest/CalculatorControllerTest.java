package com.wit.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@EmbeddedKafka(partitions = 1, topics = {"calculator-topic", "calculator-result-topic"})
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void testSum() throws Exception {
        mockMvc.perform(post("/api/calculator/sum")
                .param("a", "2.5")
                .param("b", "3.5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Resultado da soma: 6.0"));
    }

    @Test
    public void testSubtract() throws Exception {
        mockMvc.perform(post("/api/calculator/subtract")
                .param("a", "5.5")
                .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Resultado da subtração: 2.5"));
    }
}
