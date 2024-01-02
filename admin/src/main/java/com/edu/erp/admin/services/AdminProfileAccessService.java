package com.edu.erp.admin.services;

import com.edu.erp.admin.dtos.AdminModuleDTO;
import com.edu.erp.admin.dtos.AdminProfileAccessDTO;
import com.edu.erp.admin.dtos.AdminProfileModuleDTO;
import com.edu.erp.admin.dtos.AdminUsersRecordDTO;
import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminProfileAccess;
import com.edu.erp.admin.repositories.AdminModulesRepository;
import com.edu.erp.admin.repositories.AdminProfileAccessRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminProfileAccessService {
    final AdminProfileAccessRepository repository;
    final AdminModulesRepository moduleRepository;

    public AdminProfileAccessService(AdminProfileAccessRepository adminProfileAccessRepository, AdminModulesRepository moduleRepository) {
        this.repository = adminProfileAccessRepository;
        this.moduleRepository = moduleRepository;
    }

    public AdminProfileAccess save(AdminProfileAccess adminProfileAccess) {
        return repository.save(adminProfileAccess);
    }


    public Page<AdminProfileAccess> findAll(Pageable pageable) {
        return repository.findByDateDeletionIsNull(pageable);
    }

    public Optional<AdminProfileAccess> findById(UUID id) {
        return repository.findById(id);
    }

    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            Optional<AdminProfileAccess> profileAccess = repository.findById(id);
            if (profileAccess.isPresent()) {
                profileAccess.get().setDateDeletion(new Date());
                repository.save(profileAccess.get());
                return true;
            }
        }
        return false;
    }

    public AdminProfileAccess update(UUID id, AdminProfileAccessDTO dto) {
        if (repository.existsById(id)) {
            Optional<AdminProfileAccess> adminProfileAccess = repository.findById(id);
            if (adminProfileAccess.isPresent()) {
                AdminProfileAccess profileAccess = adminProfileAccess.get();
                profileAccess.setName(dto.name());
                repository.save(profileAccess);
                return profileAccess;
            }
        }
        return null;
    }

    public AdminProfileAccess addModule(AdminProfileModuleDTO dto) {
        Optional<AdminProfileAccess> profiles = repository.findById(dto.idProfile());
        Optional<AdminModules> modules = moduleRepository.findById(dto.idModule());

        if (profiles.isPresent() && modules.isPresent()) {
            AdminProfileAccess profile = profiles.get();
            AdminModules module = modules.get();

            if (profile.getDateDeletion() != null || module.getDateDeletion() != null) {
                return null;
            }
            if (profile.getAdminModules() != null && !profile.getAdminModules().contains(module)) {
                profile.getAdminModules().add(module);
            } else {
                profile.getAdminModules().remove(module);
            }
            try {
                return repository.save(profile);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return null;
    }
}
