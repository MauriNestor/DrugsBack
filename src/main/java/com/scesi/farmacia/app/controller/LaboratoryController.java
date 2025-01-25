package com.scesi.farmacia.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scesi.farmacia.app.model.Laboratory;
import com.scesi.farmacia.app.service.LaboratoryService;

@Controller
@RequestMapping("/laboratories")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @PostMapping
    public ResponseEntity<Laboratory> createLaboratory(@RequestBody Laboratory laboratory) {
        Laboratory newLaboratory = laboratoryService.createLaboratory(laboratory);
        return ResponseEntity.ok(newLaboratory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratory> getLaboratory(@PathVariable Long id) {
        Laboratory laboratory = laboratoryService.getLaboratoryById(id);
        return ResponseEntity.ok(laboratory);
    }

    @GetMapping
    public ResponseEntity<List<Laboratory>> getAllLaboratories() {
        List<Laboratory> laboratories = laboratoryService.getAllLaboratories();
        return ResponseEntity.ok(laboratories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLaboratory(@PathVariable Long id) {
        laboratoryService.deleteLaboratory(id);
        return ResponseEntity.ok("Laboratory deleted successfully.");
    }

}
