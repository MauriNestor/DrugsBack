package com.scesi.farmacia.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadingController {

    @GetMapping("/saludo/{name}")
    public String getMethodName(@PathVariable String name) {
        return "Hola " + name;
    }
}
