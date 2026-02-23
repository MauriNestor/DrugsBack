package com.scesi.farmacia.app.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductUpdateRequestDTO {
    @Size(min = 1, message = "nameProduct must not be empty")
    private String nameProduct;

    @PositiveOrZero(message = "priceProduct must be >= 0")
    private Double priceProduct;

    private String lote;

    @PositiveOrZero(message = "amount must be >= 0")
    private Integer amount;

    private LocalDate expiration;

    @Size(min = 1, message = "composition must not be empty")
    private String composition;

    private String description;

    private Long laboratoryId;
}
