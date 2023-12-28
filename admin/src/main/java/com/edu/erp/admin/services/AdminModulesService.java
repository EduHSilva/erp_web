package com.edu.erp.admin.services;

import com.edu.erp.admin.dtos.AdminModuleDTO;
import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.repositories.AdminModulesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminModulesService {
    final AdminModulesRepository repository;

    public AdminModulesService(AdminModulesRepository adminUsersRepository) {
        this.repository = adminUsersRepository;
    }

    @Transactional
    public AdminModules save(AdminModules adminModules) {
        if (repository.findByNameAndDateDeletionIsNull(adminModules.getName()) == null)
            return repository.save(adminModules);
        else return null;
    }

    public Page<AdminModules> findAll(Pageable pageable) {
        return repository.findByDateDeletionIsNull(pageable);
    }

    public Optional<AdminModules> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            Optional<AdminModules> module = repository.findById(id);
            if (module.isPresent()) {
                module.get().setDateDeletion(new Date());
                repository.save(module.get());
                return true;
            }
        }
        return false;
    }

    @Transactional
    public AdminModules update(UUID id, AdminModuleDTO dto) {
        if (repository.existsById(id)) {
            Optional<AdminModules> modules = repository.findById(id);
            if (modules.isPresent()) {
                AdminModules module = modules.get();
                module.setName(dto.name());
                repository.save(module);
                return module;
            }
        }
        return null;
    }

}
