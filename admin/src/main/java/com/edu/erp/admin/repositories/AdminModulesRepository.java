package com.edu.erp.admin.repositories;

import com.edu.erp.admin.models.AdminModules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminModulesRepository extends JpaRepository<AdminModules, UUID> {
}
