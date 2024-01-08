package com.edu.erp.sales.controllers;

import com.edu.erp.sales.dto.SalesOrderDTO;
import com.edu.erp.sales.dto.SalesOrderItemDTO;
import com.edu.erp.sales.dto.SalesProductDTO;
import com.edu.erp.sales.models.SalesOrderItems;
import com.edu.erp.sales.models.SalesOrders;
import com.edu.erp.sales.models.SalesProducts;
import com.edu.erp.sales.services.SalesOrdersService;
import com.edu.erp.sales.services.SalesProductsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "Orders")
@CrossOrigin("*")
@RequestMapping("/sales/orders")
public class SalesOrdersController {

    private final SalesOrdersService service;


    public SalesOrdersController(SalesOrdersService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<Page<SalesOrders>> getOrders(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping()
    public ResponseEntity<SalesOrders> save(@RequestBody @Valid SalesOrderDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesOrders> update(@PathVariable UUID id, @Valid @RequestBody SalesOrderDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PostMapping("/item")
    public ResponseEntity<SalesOrderItems> addItem(@Valid @RequestBody SalesOrderItemDTO dto) {
        return ResponseEntity.ok(service.addItem(dto));
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<SalesOrderItems> editItem(@PathVariable UUID id, @Valid @RequestBody SalesOrderItemDTO dto) {
        return ResponseEntity.ok(service.editItem(id, dto));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity removeItem(@PathVariable UUID id) {
        return ResponseEntity.ok(service.removeItem(id));
    }
}
