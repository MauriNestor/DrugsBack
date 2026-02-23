package com.scesi.farmacia.app.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.scesi.farmacia.app.dto.LaboratoryDTO;
import com.scesi.farmacia.app.dto.LaboratoryRequestDTO;
import com.scesi.farmacia.app.service.LaboratoryService;

@RestController
@RequestMapping({ "/api/v1/laboratorios", "/laboratories" })
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @PostMapping
    public ResponseEntity<LaboratoryDTO> createLaboratory(@Valid @RequestBody LaboratoryRequestDTO laboratoryRequestDTO) {
        LaboratoryDTO newLaboratory = laboratoryService.createLaboratoryFromDTO(laboratoryRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newLaboratory.getLaboratoryId())
                .toUri();
        return ResponseEntity.created(location).body(newLaboratory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> getLaboratory(@PathVariable Long id) {
        LaboratoryDTO laboratory = laboratoryService.getLaboratoryById(id);
        return ResponseEntity.ok(laboratory);
    }

    @GetMapping
    public ResponseEntity<List<LaboratoryDTO>> getAllLaboratories() {
        List<LaboratoryDTO> laboratories = laboratoryService.getAllLaboratories();
        return ResponseEntity.ok(laboratories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratory(@PathVariable Long id) {
        laboratoryService.deleteLaboratory(id);
        return ResponseEntity.noContent().build();
    }

}
