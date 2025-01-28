package com.scesi.farmacia.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;

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

    @OneToMany(mappedBy = "laboratory", cascade = CascadeType.ALL)
    private List<Product> products;
}
