package com.edu.erp.admin.services;

import com.edu.erp.admin.dtos.AdminModuleDTO;
import com.edu.erp.admin.dtos.AdminUsersRecordDTO;
import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminModulesRepository;
import com.edu.erp.admin.repositories.AdminUsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
       return repository.save(adminModules);
    }

    public List<AdminModules> findAll() {
        return repository.findAll();
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
                BeanUtils.copyProperties(dto, module);
                repository.save(module);
                return module;
            }
        }
        return null;
    }

}
