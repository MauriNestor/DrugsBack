package com.scesi.farmacia.app.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.scesi.farmacia.app.dto.ProductDTO;
import com.scesi.farmacia.app.dto.ProductCreateRequestDTO;
import com.scesi.farmacia.app.dto.ProductUpdateRequestDTO;
import com.scesi.farmacia.app.model.Product;
import com.scesi.farmacia.app.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping({ "/api/v1/productos", "/productos" })
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductos() {
        List<ProductDTO> products = productService.listProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> crearProducto(@Valid @RequestBody ProductCreateRequestDTO productRequestDTO) {
        ProductDTO productDTO = productService.createProductFromDTO(productRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productDTO.getIdProduct())
                .toUri();
        return ResponseEntity.created(location).body(productDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = new ProductDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequestDTO productRequestDTO) {

        ProductDTO updatedProductDTO = productService.updateProductFromDTO(id, productRequestDTO);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> patchProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequestDTO productRequestDTO) {

        ProductDTO updatedProductDTO = productService.updateProductFromDTO(id, productRequestDTO);
        return ResponseEntity.ok(updatedProductDTO);
    }

}
