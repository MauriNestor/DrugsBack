package com.scesi.farmacia.app.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductCreateRequestDTO {
    @NotBlank(message = "nameProduct is required")
    private String nameProduct;

    @PositiveOrZero(message = "priceProduct must be >= 0")
    private Double priceProduct;

    private String lote;

    @PositiveOrZero(message = "amount must be >= 0")
    private Integer amount;

    private LocalDate expiration;

    @NotBlank(message = "composition is required")
    private String composition;

    private String description;

    @NotNull(message = "laboratoryId is required")
    private Long laboratoryId;
}
