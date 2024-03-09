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
@RequestMapping("/sales")
public class SalesOrdersController {

    private final SalesOrdersService service;


    public SalesOrdersController(SalesOrdersService service) {
        this.service = service;
    }

    @GetMapping("/orders")
    public ResponseEntity<Page<SalesOrders>> getOrders(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<SalesOrders> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping("/orders")
    public ResponseEntity<SalesOrders> save(@RequestBody @Valid SalesOrderDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<SalesOrders> update(@PathVariable Long id, @Valid @RequestBody SalesOrderDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PostMapping("/orders/item")
    public ResponseEntity<SalesOrderItems> addItem(@Valid @RequestBody SalesOrderItemDTO dto) {
        return ResponseEntity.ok(service.addItem(dto));
    }

    @DeleteMapping("/orders/item/{id}")
    public ResponseEntity removeItem(@PathVariable UUID id) {
        return ResponseEntity.ok(service.removeItem(id));
    }
}
