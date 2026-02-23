package com.scesi.farmacia.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LaboratoryRequestDTO {
    @NotBlank(message = "laboratoryName is required")
    private String laboratoryName;

    private String phone;
}
