package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BasicApplication {  

    private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(BasicApplication.class, args);
            log.info("🚀 Aplicación iniciada correctamente.");
        } catch (Exception e) {
            log.error("❌ Error al iniciar la aplicación: {}", e.getMessage(), e);
        }
    }
}






