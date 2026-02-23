package com.scesi.farmacia.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scesi.farmacia.app.dto.LaboratoryDTO;
import com.scesi.farmacia.app.dto.LaboratoryRequestDTO;
import com.scesi.farmacia.app.exception.ResourceNotFoundException;
import com.scesi.farmacia.app.model.Laboratory;
import com.scesi.farmacia.app.repository.LaboratoryRepository;

@Service
public class LaboratoryService {

    @Autowired
    LaboratoryRepository laboratoryRepository;

    public LaboratoryDTO createLaboratoryFromDTO(LaboratoryRequestDTO laboratoryRequestDTO) {
        Laboratory laboratory = new Laboratory();
        laboratory.setLaboratoryName(laboratoryRequestDTO.getLaboratoryName());
        laboratory.setPhone(laboratoryRequestDTO.getPhone());

        Laboratory saved = laboratoryRepository.save(laboratory);
        return toDTO(saved);
    }

    public void deleteLaboratory(Long laboratoryId) {
        if (!laboratoryRepository.existsById(laboratoryId)) {
            throw new ResourceNotFoundException("Laboratory with ID " + laboratoryId + " not found.");
        }
        laboratoryRepository.deleteById(laboratoryId);
    }

    public LaboratoryDTO getLaboratoryById(Long laboratoryId) {
        Laboratory laboratory = laboratoryRepository.findById(laboratoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratory with ID " + laboratoryId + " not found."));
        return toDTO(laboratory);
    }

    public List<LaboratoryDTO> getAllLaboratories() {
        return laboratoryRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private LaboratoryDTO toDTO(Laboratory laboratory) {
        return new LaboratoryDTO(laboratory.getLaboratoryId(), laboratory.getLaboratoryName(), laboratory.getPhone());
    }
}
