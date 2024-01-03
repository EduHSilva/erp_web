package com.edu.erp.admin.repositories;

import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminProfileAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AdminProfileAccessRepository extends JpaRepository<AdminProfileAccess, UUID> {
    Page<AdminProfileAccess> findByDateDeletionIsNull(Pageable pageable);
    List<AdminProfileAccess> findByDateDeletionIsNull();

}
