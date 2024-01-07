package com.edu.erp.sales.repositories;

import com.edu.erp.sales.models.SalesPersons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalesPersonsRepository extends JpaRepository<SalesPersons, UUID> {
}
