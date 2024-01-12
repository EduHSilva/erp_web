package com.edu.erp.sales.controllers;

import com.edu.erp.sales.models.SalesCities;
import com.edu.erp.sales.repositories.SalesCitiesRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Cities")
@CrossOrigin("*")
@RequestMapping("/sales")
public class SalesCitiesController {

    private final SalesCitiesRepository repository;

    public SalesCitiesController(SalesCitiesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cities")
    public ResponseEntity<Page<SalesCities>> getCities(Pageable pageable, @RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByDateDeletionIsNullAndNameContainingIgnoreCase(name, pageable));
    }
}
