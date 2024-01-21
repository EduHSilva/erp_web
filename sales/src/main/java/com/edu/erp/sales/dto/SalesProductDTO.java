package com.edu.erp.sales.dto;

import com.edu.erp.sales.enums.StatusProduct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalesProductDTO(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull Double price,

        Integer stock,

        String img_url,

        StatusProduct status
) {
}
