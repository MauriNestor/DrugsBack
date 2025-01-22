package com.scesi.farmacia.app.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos", uniqueConstraints = @UniqueConstraint(columnNames = { "nombre_producto", "composicion" }))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProduct;

    @Column(name = "nombre_producto", nullable = false)
    private String nameProduct;

    @Column(name = "precio")
    private Double priceProduct;

    @Column(name = "id_usuario")
    private Long idUser;

    @Column(name = "lote")
    private String lote;

    @Column(name = "cantidad")
    private Integer amount;

    @Column(name = "vencimiento")
    private LocalDate expiration;

    @Column(name = "composicion")
    private String composition;

    @Column(name = "descripcion")
    private String description;

}
