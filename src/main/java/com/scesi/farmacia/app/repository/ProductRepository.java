package com.scesi.farmacia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.scesi.farmacia.app.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
