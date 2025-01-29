package com.scesi.farmacia.app.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String nameProduct;
    private Double priceProduct;
    private String lote;
    private Integer amount;
    private LocalDate expiration;
    private String composition;
    private String description;
    private Long laboratoryId;
}
