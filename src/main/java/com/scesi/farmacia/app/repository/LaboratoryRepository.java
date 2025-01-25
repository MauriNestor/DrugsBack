package com.scesi.farmacia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scesi.farmacia.app.model.Laboratory;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
}