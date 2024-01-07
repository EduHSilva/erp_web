package com.edu.erp.sales.repositories;

import com.edu.erp.sales.models.SalesStates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalesStatesRepository extends JpaRepository<SalesStates, UUID> {
}
