package com.scesi.farmacia.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadingController {

    @GetMapping("/saludo/{name}")
    public String getMethodName(@PathVariable String name) {
        boolean result = verificarPal(name);
        if (result) {
            return "Hola " + name + ", tu nombre es palíndromo";
        } else {
            return "Hola " + name + ", tu nombre no es palíndromo";
        }
    }

    private boolean verificarPal(String text) {
        String cleanText = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleanText.equals(new StringBuilder(cleanText).reverse().toString());
    }
}