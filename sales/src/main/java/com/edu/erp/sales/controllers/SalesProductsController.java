package com.edu.erp.sales.controllers;

import com.edu.erp.sales.dto.SalesProductDTO;
import com.edu.erp.sales.models.SalesProducts;
import com.edu.erp.sales.services.SalesProductsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@Tag(name = "Products")
@CrossOrigin("*")
@RequestMapping("/sales")
public class SalesProductsController {

    private final SalesProductsService service;

    public SalesProductsController(SalesProductsService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<Page<SalesProducts>> get(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<SalesProducts> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping("/products")
    public ResponseEntity<SalesProducts> save(@RequestBody @Valid SalesProductDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<SalesProducts> update(@PathVariable UUID id, @Valid @RequestBody SalesProductDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
