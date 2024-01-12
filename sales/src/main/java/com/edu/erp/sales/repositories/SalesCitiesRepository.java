package com.edu.erp.sales.repositories;

import com.edu.erp.sales.models.SalesCities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalesCitiesRepository extends JpaRepository<SalesCities, UUID> {

    Page<SalesCities> findByDateDeletionIsNullAndNameContainingIgnoreCase(String name, Pageable pageable);
}
