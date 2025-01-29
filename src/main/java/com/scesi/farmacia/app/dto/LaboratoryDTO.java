package com.scesi.farmacia.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDTO {
    private Long laboratoryId;
    private String laboratoryName;
    private String phone;
}
