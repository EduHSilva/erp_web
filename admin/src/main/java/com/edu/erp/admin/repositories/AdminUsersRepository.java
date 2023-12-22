package com.edu.erp.admin.repositories;

import com.edu.erp.admin.models.AdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface AdminUsersRepository extends JpaRepository<AdminUsers, UUID> {
    UserDetails findByEmail(String email);
}
