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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
}
