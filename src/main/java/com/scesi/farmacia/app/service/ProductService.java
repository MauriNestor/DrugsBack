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
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        existingProduct.setNameProduct(productRequestDTO.getNameProduct());
        existingProduct.setPriceProduct(productRequestDTO.getPriceProduct());
        existingProduct.setLote(productRequestDTO.getLote());
        existingProduct.setAmount(productRequestDTO.getAmount());
        existingProduct.setExpiration(productRequestDTO.getExpiration());
        existingProduct.setComposition(productRequestDTO.getComposition());
        existingProduct.setDescription(productRequestDTO.getDescription());

        Laboratory laboratory = laboratoryRepository.findById(productRequestDTO.getLaboratoryId())
                .orElseThrow(() -> new IllegalArgumentException("Laboratory not found"));
        existingProduct.setLaboratory(laboratory);

        Product updatedProduct = productRepository.save(existingProduct);
        return new ProductDTO(updatedProduct);
    }

}
