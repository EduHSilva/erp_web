package com.edu.erp.admin.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record AdminProfileAccessDTO(
        @NotBlank String name
) {
}
