package com.edu.erp.sales.controllers;

import com.edu.erp.sales.dto.SalesPersonDTO;
import com.edu.erp.sales.models.SalesPersons;
import com.edu.erp.sales.services.SalesPersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "Persons")
@CrossOrigin("*")
@RequestMapping("/sales/persons")
public class SalesPersonController {

    private final SalesPersonService service;

    public SalesPersonController(SalesPersonService service) {
        this.service = service;
    }

    @GetMapping("/clients")
    public ResponseEntity<Page<SalesPersons>> getClients(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findClients(pageable));
    }

    @GetMapping("/sellers")
    public ResponseEntity<Page<SalesPersons>> getSellers(Pageable pageable) {
        return ResponseEntity.ok(service.findSellers(pageable));
    }


    @PostMapping()
    public ResponseEntity<SalesPersons> save(@RequestBody @Valid SalesPersonDTO dto) {
        SalesPersons person = new SalesPersons();
        BeanUtils.copyProperties(dto, person);

        return ResponseEntity.ok(service.save(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesPersons> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesPersons> update(@PathVariable UUID id, @Valid @RequestBody SalesPersonDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
