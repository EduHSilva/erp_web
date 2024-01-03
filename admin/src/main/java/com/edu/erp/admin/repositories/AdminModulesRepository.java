package com.edu.erp.admin.repositories;

import com.edu.erp.admin.models.AdminModules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AdminModulesRepository extends JpaRepository<AdminModules, UUID> {
    public AdminModules findByNameAndDateDeletionIsNull(String name);

    Page<AdminModules> findByDateDeletionIsNull(Pageable pageRequest);

    List<AdminModules> findByDateDeletionIsNull();

}
