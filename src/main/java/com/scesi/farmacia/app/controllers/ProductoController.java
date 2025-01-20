package com.scesi.farmacia.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scesi.farmacia.app.domain.Producto;
import com.scesi.farmacia.app.repository.ProductoRepository;

@RestController

@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoService;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.listarProductos();
    }
}
