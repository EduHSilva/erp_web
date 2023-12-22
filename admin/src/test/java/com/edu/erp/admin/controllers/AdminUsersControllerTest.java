package com.edu.erp.admin.controllers;

import com.edu.erp.admin.config.security.SecurityFilter;
import com.edu.erp.admin.config.security.TokenService;
import com.edu.erp.admin.enums.Language;
import com.edu.erp.admin.enums.Status;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminUsersRepository;
import com.edu.erp.admin.services.AdminUsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
@Import({SecurityFilter.class, AdminUsersController.class, AuthController.class})
class AdminUsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdminUsersService adminUsersService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private AdminUsersRepository adminUsersRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testGetUserById() throws Exception {
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

        Mockito.when(adminUsersService.findById(userId)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/admin/user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.name").value("John Doe"));

        Mockito.verify(adminUsersService, Mockito.times(1)).findById(userId);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testCreateUser() throws Exception {
        UUID userId = UUID.randomUUID();
        AdminUsers createdUser = new AdminUsers(userId, "John Doe",
                "123.456.789-00",
                new Date(),
                null,
                Language.EN,
                Status.ACTIVE,
                true,
                "john.doe@example.com",
                "123",
                "123-456-7890");

        Mockito.when(adminUsersService.saveUser(Mockito.any())).thenReturn(createdUser);

        mockMvc.perform(post("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(createdUser.getId().toString()))
                .andExpect(jsonPath("$.name").value("John Doe"));

        Mockito.verify(adminUsersService, Mockito.times(1)).saveUser(Mockito.any());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testUpdateUser() throws Exception {
        UUID userId = UUID.randomUUID();
        AdminUsers updatedUser = new AdminUsers(userId, "Updated Name",
                "123.456.789-00",
                new Date(),
                null,
                Language.EN,
                Status.ACTIVE,
                true,
                "john.doe@example.com",
                "123",
                "123-456-7890");

        Mockito.when(adminUsersService.updateUser(Mockito.any(), Mockito.any())).thenReturn(updatedUser);

        mockMvc.perform(put("/admin/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(updatedUser.getId().toString()))
                .andExpect(jsonPath("$.name").value("Updated Name"));

        Mockito.verify(adminUsersService, Mockito.times(1)).updateUser(Mockito.eq(userId), Mockito.any());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testDeleteUser() throws Exception {
        UUID userId = UUID.randomUUID();

        Mockito.when(adminUsersService.deleteUser(userId)).thenReturn(true);

        mockMvc.perform(delete("/admin/user/{id}", userId))
                .andExpect(status().isOk());

        Mockito.verify(adminUsersService, Mockito.times(1)).deleteUser(userId);
    }
}
