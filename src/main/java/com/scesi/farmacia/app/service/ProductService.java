package com.scesi.farmacia.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scesi.farmacia.app.dto.ProductDTO;
import com.scesi.farmacia.app.dto.ProductCreateRequestDTO;
import com.scesi.farmacia.app.dto.ProductUpdateRequestDTO;
import com.scesi.farmacia.app.exception.ConflictException;
import com.scesi.farmacia.app.exception.ResourceNotFoundException;
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

    public ProductDTO createProductFromDTO(ProductCreateRequestDTO productRequestDTO) {
        Laboratory laboratory = laboratoryRepository.findById(productRequestDTO.getLaboratoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
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
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found."));
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with ID " + id + " not found.");
        }
        productRepository.deleteById(id);
    }

    public ProductDTO updateProductFromDTO(Long id, ProductUpdateRequestDTO productRequestDTO) {
        // Verificar si el producto existe
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found."));

        Laboratory laboratory = existingProduct.getLaboratory();
        if (productRequestDTO.getLaboratoryId() != null) {
            laboratory = laboratoryRepository.findById(productRequestDTO.getLaboratoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Laboratory with ID " + productRequestDTO.getLaboratoryId() + " not found."));
        }

        String effectiveName = productRequestDTO.getNameProduct() != null ? productRequestDTO.getNameProduct()
                : existingProduct.getNameProduct();
        String effectiveComposition = productRequestDTO.getComposition() != null ? productRequestDTO.getComposition()
                : existingProduct.getComposition();

        boolean exists = productRepository.existsByNameProductAndComposition(effectiveName, effectiveComposition);
        boolean isSameKey = effectiveName.equals(existingProduct.getNameProduct())
                && effectiveComposition.equals(existingProduct.getComposition());
        if (exists && !isSameKey) {
            throw new ConflictException("A product with the same name and composition already exists.");
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

        existingProduct.setLaboratory(laboratory);

        // Guardar en la base de datos
        Product updatedProduct = productRepository.save(existingProduct);

        // Convertir a DTO antes de devolverlo
        return new ProductDTO(updatedProduct);
    }

}
