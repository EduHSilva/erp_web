package com.edu.erp.sales.repositories;

import com.edu.erp.sales.models.SalesOrderItems;
import com.edu.erp.sales.models.SalesOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalesOrderItemsRepository extends JpaRepository<SalesOrderItems, UUID> {
}
