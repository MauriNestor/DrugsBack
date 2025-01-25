package com.scesi.farmacia.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scesi.farmacia.app.model.Laboratory;
import com.scesi.farmacia.app.repository.LaboratoryRepository;

@Service
public class LaboratoryService {

    @Autowired
    LaboratoryRepository laboratoryRepository;

    public Laboratory createLaboratory(Laboratory laboratory) {
        return laboratoryRepository.save(laboratory);
    }

    public void deleteLaboratory(Long laboratoryId) {
        if (!laboratoryRepository.existsById(laboratoryId)) {
            throw new IllegalArgumentException("Laboratory with ID " + laboratoryId + " does not exist.");
        }
        laboratoryRepository.deleteById(laboratoryId);
    }

    public Laboratory getLaboratoryById(Long laboratoryId) {
        return laboratoryRepository.findById(laboratoryId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Laboratory with ID " + laboratoryId + " does not exist."));
    }

    public List<Laboratory> getAllLaboratories() {
        return laboratoryRepository.findAll();
    }
}
