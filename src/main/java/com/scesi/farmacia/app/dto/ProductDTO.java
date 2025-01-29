package com.scesi.farmacia.app.dto;

import com.scesi.farmacia.app.model.Product;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDTO {
    private Long idProduct;
    private String nameProduct;
    private Double priceProduct;
    private String lote;
    private Integer amount;
    private LocalDate expiration;
    private String composition;
    private String description;
    private String laboratoryName;

    public ProductDTO(Product product) {
        this.idProduct = product.getIdProduct();
        this.nameProduct = product.getNameProduct();
        this.priceProduct = product.getPriceProduct();
        this.lote = product.getLote();
        this.amount = product.getAmount();
        this.expiration = product.getExpiration();
        this.composition = product.getComposition();
        this.description = product.getDescription();
        this.laboratoryName = (product.getLaboratory() != null) ? product.getLaboratory().getLaboratoryName() : null;
    }
}
