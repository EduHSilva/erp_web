package com.edu.erp.sales.controllers;

import com.edu.erp.sales.dto.SalesProductDTO;
import com.edu.erp.sales.models.SalesProducts;
import com.edu.erp.sales.services.SalesProductsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "Products")
@CrossOrigin("*")
@RequestMapping("/sales/products")
public class SalesProductsController {

    private final SalesProductsService service;

    public SalesProductsController(SalesProductsService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<Page<SalesProducts>> getSellers(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping()
    public ResponseEntity<SalesProducts> save(@RequestBody @Valid SalesProductDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesProducts> update(@PathVariable UUID id, @Valid @RequestBody SalesProductDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
