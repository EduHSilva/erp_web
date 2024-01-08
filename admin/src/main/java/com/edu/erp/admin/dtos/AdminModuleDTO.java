package com.edu.erp.admin.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record AdminModuleDTO(@NotBlank String name) {

    public AdminModuleDTO(String name) {
        this.name = name;
    }
}
