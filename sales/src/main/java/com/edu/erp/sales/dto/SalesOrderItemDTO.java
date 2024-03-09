package com.edu.erp.sales.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record SalesOrderItemDTO(
        UUID id,
        @NotNull @Positive Integer quantity,
        @NotNull @Positive Double priceUnit,
        @NotNull UUID productID,
        Long orderID

) {
}
