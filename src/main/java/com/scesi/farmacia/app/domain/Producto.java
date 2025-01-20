package com.scesi.farmacia.app.domain;

import jakarta.persistence.Entity;

@Entity
public class Producto {

    private Long id;

    private String nombre;
    private Double precio;
    private Integer stock;

    // Getters y Setters
}
