package com.scesi.farmacia.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.scesi.farmacia.app.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CustomerController {
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer("John", "Doe", "perrito@gmail.com", 1),
            new Customer("John", "Doe", "perrito@gmail.com", 2),
            new Customer("John", "Doe", "perrito@gmail.com", 3)));

    @GetMapping("clientes")
    public List<Customer> getCustomers() {
        return customers;
    }

}
