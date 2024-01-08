package com.edu.erp.admin.services;

import com.edu.erp.admin.dtos.AdminUsersRecordDTO;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminUsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminUsersService {
    final AdminUsersRepository adminUsersRepository;

    public AdminUsersService(AdminUsersRepository adminUsersRepository) {
        this.adminUsersRepository = adminUsersRepository;
    }


    public AdminUsers save(AdminUsers userModel) {
        if(adminUsersRepository.findByEmail(userModel.getEmail()) == null) {
            BCryptPasswordEncoder cripto = new BCryptPasswordEncoder();

            userModel.setPassword(cripto.encode(userModel.getPassword()));
            userModel.setDateCreated(new Date());
            userModel = adminUsersRepository.save(userModel);
            return userModel;
        }
        return null;
    }


    public Page<AdminUsers> findAll(Pageable pageable) {
        return adminUsersRepository.findByDateDeletionIsNull(pageable);
    }

    public Optional<AdminUsers> findById(UUID id) {
        return adminUsersRepository.findById(id);
    }

    @Transactional
    public boolean delete(UUID id) {
        if (adminUsersRepository.existsById(id)) {
            Optional<AdminUsers> user = adminUsersRepository.findById(id);
            if (user.isPresent()) {
                user.get().setDateDeletion(new Date());
                adminUsersRepository.save(user.get());
                return true;
            }
        }
        return false;
    }

    @Transactional
    public AdminUsers update(UUID id, AdminUsersRecordDTO dto) {
        if (adminUsersRepository.existsById(id)) {
            Optional<AdminUsers> user = adminUsersRepository.findById(id);
            if (user.isPresent()) {
                AdminUsers adminUsers = user.get();
                BeanUtils.copyProperties(dto, adminUsers);
                adminUsersRepository.save(user.get());
                return adminUsers;
            }
        }
        return null;
    }

}
