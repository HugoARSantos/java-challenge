 
package com.example.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorControllerTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testSum() {
        ResponseEntity<Map> response = restTemplate.getForEntity("/calculator/sum?a=2&b=3", Map.class);
        assertEquals(new BigDecimal("5"), new BigDecimal(response.getBody().get("result").toString()));
    }
}