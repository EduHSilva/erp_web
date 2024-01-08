package com.edu.erp.sales.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record SalesOrderItemDTO(
        @NotNull @Positive Integer quantity,
        @NotNull @Positive Double priceUnit,
        @NotNull UUID productID,
        @NotNull UUID orderID

) {
}
