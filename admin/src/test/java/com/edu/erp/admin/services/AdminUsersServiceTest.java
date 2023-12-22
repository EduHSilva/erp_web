package com.edu.erp.admin.services;

import com.edu.erp.admin.dtos.AdminUsersRecordDTO;
import com.edu.erp.admin.enums.Language;
import com.edu.erp.admin.enums.Status;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminUsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdminUsersServiceTest {

    @Mock
    private AdminUsersRepository adminUsersRepository;

    @InjectMocks
    private AdminUsersService adminUsersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_Success() {
        AdminUsersRecordDTO dto = new AdminUsersRecordDTO(
                "John Doe",
                "john.doe@example.com",
                "123.456.789-00",
                Language.EN,
                Status.ACTIVE,
                new Date(),
                null,
                true,
                "123",
                "123-456-7890");

        AdminUsers user = new AdminUsers();
        BeanUtils.copyProperties(dto, user);
        when(adminUsersRepository.findByEmail(any())).thenReturn(null);
        when(adminUsersRepository.save(any())).thenReturn(user);


        AdminUsers savedUser = adminUsersService.saveUser(user);

        assertNotNull(savedUser);
        verify(adminUsersRepository, times(1)).findByEmail(any());
        verify(adminUsersRepository, times(1)).save(any());
    }

    @Test
    void saveUser_UserExists() {
        AdminUsersRecordDTO dto = new AdminUsersRecordDTO(
                "John Doe",
                "john.doe@example.com",
                "123.456.789-00",
                Language.EN,
                Status.ACTIVE,
                new Date(),
                null,
                true,
                "123",
                "123-456-7890");

        AdminUsers user = new AdminUsers();
        BeanUtils.copyProperties(dto, user);
        when(adminUsersRepository.findByEmail(any())).thenReturn(user);

        AdminUsers savedUser = adminUsersService.saveUser(user);

        assertNull(savedUser);
        verify(adminUsersRepository, times(1)).findByEmail(any());
        verify(adminUsersRepository, never()).save(any());
    }

    @Test
    void deleteUser_UserNotExists() {
        UUID userId = UUID.randomUUID();

        when(adminUsersRepository.existsById(any())).thenReturn(false);

        boolean deleted = adminUsersService.deleteUser(userId);

        assertFalse(deleted);
    }

    @Test
    void deleteUser_Success() {
        UUID userId = UUID.randomUUID();

        AdminUsers user = new AdminUsers(userId, "John Doe",
                "123.456.789-00",
                new Date(),
                null,
                Language.EN,
                Status.ACTIVE,
                true,
                "john.doe@example.com",
                "123",
                "123-456-7890");

        when(adminUsersRepository.existsById(any())).thenReturn(true);
        when(adminUsersRepository.findById(any())).thenReturn(Optional.of(user));

        boolean deleted = adminUsersService.deleteUser(userId);

        assertTrue(deleted);
    }


    @Test
    void updateUser_UserNotExists() {
        UUID userId = UUID.randomUUID();
        AdminUsersRecordDTO dto = new AdminUsersRecordDTO(
                "John Doe",
                "john.doe@example.com",
                "123.456.789-00",
                Language.EN,
                Status.ACTIVE,
                new Date(),
                null,
                true,
                "123",
                "123-456-7890");

        AdminUsers user = new AdminUsers();
        user.setId(userId);
        BeanUtils.copyProperties(dto, user);

        when(adminUsersRepository.existsById(any())).thenReturn(false);

        AdminUsers userUpdated = adminUsersService.updateUser(userId, dto);

        assertNull(userUpdated);
    }

    @Test
    void updateUser_Success() {
        UUID userId = UUID.randomUUID();
        AdminUsersRecordDTO dto = new AdminUsersRecordDTO(
                "John Doe",
                "john.doe@example.com",
                "123.456.789-00",
                Language.EN,
                Status.ACTIVE,
                new Date(),
                null,
                true,
                "123",
                "123-456-7890");

        AdminUsers user = new AdminUsers();
        user.setId(userId);
        BeanUtils.copyProperties(dto, user);

        when(adminUsersRepository.existsById(any())).thenReturn(true);
        when(adminUsersRepository.findById(any())).thenReturn(Optional.of(user));

        AdminUsers userUpdated = adminUsersService.updateUser(userId, dto);

        assertNotNull(userUpdated);
    }

}
