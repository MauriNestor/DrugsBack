package com.scesi.farmacia.app.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class holacontroller {
    @GetMapping("/")
    public String getMethodName() {
        return "Hola mundo";
    }

}
