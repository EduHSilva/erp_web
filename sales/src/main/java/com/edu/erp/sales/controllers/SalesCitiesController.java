package com.edu.erp.sales.controllers;

import com.edu.erp.sales.models.SalesCities;
import com.edu.erp.sales.models.SalesStates;
import com.edu.erp.sales.repositories.SalesCitiesRepository;
import com.edu.erp.sales.repositories.SalesStatesRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<List<SalesCities>> getCities() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
