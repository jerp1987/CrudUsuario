package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
public class BasicApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(BasicApplicationTests.class);

    @Test
    void contextLoads() {
        log.info("✅ Contexto de Spring cargado correctamente");
        assertTrue(true, "El contexto de Spring se cargó sin errores.");
    }
}








