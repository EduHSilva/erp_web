package com.edu.erp.sales.controllers;

import com.edu.erp.sales.models.SalesStates;
import com.edu.erp.sales.repositories.SalesStatesRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "States")
@CrossOrigin("*")
@RequestMapping("/sales")
public class SalesStatesController {

    private final SalesStatesRepository repository;

    public SalesStatesController(SalesStatesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/states")
    public ResponseEntity<List<SalesStates>> getStates() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
