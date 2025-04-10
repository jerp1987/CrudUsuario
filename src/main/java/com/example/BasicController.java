package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BasicController {

    private static final Logger log = LoggerFactory.getLogger(BasicController.class);

    @GetMapping("/home")
    public String home() {
        log.info("Solicitud recibida en /api/home");
        return "🚀 ¡Servidor funcionando correctamente!";
    }
}






