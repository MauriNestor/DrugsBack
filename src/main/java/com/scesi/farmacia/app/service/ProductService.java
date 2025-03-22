package com.scesi.farmacia.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scesi.farmacia.app.dto.ProductDTO;
import com.scesi.farmacia.app.dto.ProductRequestDTO;
import com.scesi.farmacia.app.model.Laboratory;
import com.scesi.farmacia.app.model.Product;
import com.scesi.farmacia.app.repository.LaboratoryRepository;
import com.scesi.farmacia.app.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public List<ProductDTO> listProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product);
    }

    public ProductDTO createProductFromDTO(ProductRequestDTO productRequestDTO) {
        Laboratory laboratory = laboratoryRepository.findById(productRequestDTO.getLaboratoryId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Laboratory with ID " + productRequestDTO.getLaboratoryId() + " not found."));

        Product product = new Product();
        product.setNameProduct(productRequestDTO.getNameProduct());
        product.setPriceProduct(productRequestDTO.getPriceProduct());
        product.setLote(productRequestDTO.getLote());
        product.setAmount(productRequestDTO.getAmount());
        product.setExpiration(productRequestDTO.getExpiration());
        product.setComposition(productRequestDTO.getComposition());
        product.setDescription(productRequestDTO.getDescription());
        product.setLaboratory(laboratory);

        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("El producto con ID " + id + " no existe.");
        }
        productRepository.deleteById(id);
    }

    public ProductDTO updateProductFromDTO(Long id, ProductRequestDTO productRequestDTO) {
        // Verificar si el producto existe
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found."));

        // Validar si el laboratorio existe
        Laboratory laboratory = laboratoryRepository.findById(productRequestDTO.getLaboratoryId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Laboratory with ID " + productRequestDTO.getLaboratoryId() + " not found."));

        // Validar duplicados (nombre y composición)
        boolean exists = productRepository.existsByNameProductAndComposition(
                productRequestDTO.getNameProduct(), productRequestDTO.getComposition());

        if (exists && !existingProduct.getNameProduct().equals(productRequestDTO.getNameProduct())) {
            throw new IllegalArgumentException("A product with the same name and composition already exists.");
        }

        // Validar que el precio y la cantidad sean positivos
        if (productRequestDTO.getPriceProduct() != null && productRequestDTO.getPriceProduct() < 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }

        if (productRequestDTO.getAmount() != null && productRequestDTO.getAmount() < 0) {
            throw new IllegalArgumentException("Amount must be a positive value.");
        }

        // Actualizar solo los campos que no son nulos
        if (productRequestDTO.getNameProduct() != null)
            existingProduct.setNameProduct(productRequestDTO.getNameProduct());
        if (productRequestDTO.getPriceProduct() != null)
            existingProduct.setPriceProduct(productRequestDTO.getPriceProduct());
        if (productRequestDTO.getLote() != null)
            existingProduct.setLote(productRequestDTO.getLote());
        if (productRequestDTO.getAmount() != null)
            existingProduct.setAmount(productRequestDTO.getAmount());
        if (productRequestDTO.getExpiration() != null)
            existingProduct.setExpiration(productRequestDTO.getExpiration());
        if (productRequestDTO.getComposition() != null)
            existingProduct.setComposition(productRequestDTO.getComposition());
        if (productRequestDTO.getDescription() != null)
            existingProduct.setDescription(productRequestDTO.getDescription());

        existingProduct.setLaboratory(laboratory); // Siempre actualizar el laboratorio

        // Guardar en la base de datos
        Product updatedProduct = productRepository.save(existingProduct);

        // Convertir a DTO antes de devolverlo
        return new ProductDTO(updatedProduct);
    }

}
