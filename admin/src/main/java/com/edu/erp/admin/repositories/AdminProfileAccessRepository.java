package com.edu.erp.admin.repositories;

import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminProfileAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminProfileAccessRepository extends JpaRepository<AdminProfileAccess, UUID> {
}
