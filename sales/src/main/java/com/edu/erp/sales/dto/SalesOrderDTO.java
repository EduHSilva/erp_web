package com.edu.erp.sales.dto;

import com.edu.erp.sales.enums.StatusOrder;
import com.edu.erp.sales.models.SalesOrders;
import com.edu.erp.sales.models.SalesPersons;
import com.edu.erp.sales.models.SalesProducts;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record SalesOrderDTO(
        StatusOrder statusOrder,
        @NotNull Double commission,
        @NotNull Date dueDate,
        @NotNull Double total,

        @NotNull SalesPersons seller,

        @NotNull SalesPersons client
) {
}
