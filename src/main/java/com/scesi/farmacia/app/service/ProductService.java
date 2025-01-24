package com.scesi.farmacia.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scesi.farmacia.app.model.Product;
import com.scesi.farmacia.app.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {

        if (productRepository.existsByNameProductAndComposition(product.getNameProduct(), product.getComposition())) {
            throw new IllegalArgumentException("Ya existe un producto con el mismo nombre y composición.");
        }
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product productoActualizado) {
        Optional<Product> productoOptional = productRepository.findById(id);

        if (productoOptional.isEmpty()) {
            throw new IllegalArgumentException("El producto con ID " + id + " no existe.");
        }

        // Obtener el producto existente
        Product productoExistente = productoOptional.get();

        // Actualizar solo los campos proporcionados
        if (productoActualizado.getNameProduct() != null) {
            productoExistente.setNameProduct(productoActualizado.getNameProduct());
        }
        if (productoActualizado.getPriceProduct() != null) {
            productoExistente.setPriceProduct(productoActualizado.getPriceProduct());
        }
        if (productoActualizado.getIdUser() != null) {
            productoExistente.setIdUser(productoActualizado.getIdUser());
        }
        if (productoActualizado.getLote() != null) {
            productoExistente.setLote(productoActualizado.getLote());
        }
        if (productoActualizado.getAmount() != null) {
            productoExistente.setAmount(productoActualizado.getAmount());
        }
        if (productoActualizado.getExpiration() != null) {
            productoExistente.setExpiration(productoActualizado.getExpiration());
        }
        if (productoActualizado.getComposition() != null) {
            productoExistente.setComposition(productoActualizado.getComposition());
        }
        if (productoActualizado.getDescription() != null) {
            productoExistente.setDescription(productoActualizado.getDescription());
        }

        return productRepository.save(productoExistente);
    }
}
