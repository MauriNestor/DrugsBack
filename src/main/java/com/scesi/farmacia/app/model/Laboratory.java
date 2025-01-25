package com.scesi.farmacia.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "laboratorios")
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laboratorio")
    private Long laboratoryId;

    @Column(name = "nombre_laboratorio", nullable = false)
    private String laboratoryName;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "id_usuario")
    private Long userId;
}
