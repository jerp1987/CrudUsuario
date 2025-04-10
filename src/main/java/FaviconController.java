package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController {

    @RequestMapping("favicon.ico")
    public ResponseEntity<Void> favicon() {
        // Retorna 204 No Content para indicar que no hay favicon disponible.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


