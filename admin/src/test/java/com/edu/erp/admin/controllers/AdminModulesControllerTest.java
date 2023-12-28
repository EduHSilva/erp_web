package com.edu.erp.admin.controllers;

import com.edu.erp.admin.config.security.SecurityFilter;
import com.edu.erp.admin.config.security.TokenService;
import com.edu.erp.admin.enums.Language;
import com.edu.erp.admin.enums.Status;
import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminUsersRepository;
import com.edu.erp.admin.services.AdminModulesService;
import com.edu.erp.admin.services.AdminProfileAccessService;
import com.edu.erp.admin.services.AdminUsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class, controllers = AdminModulesController.class)
@Import({SecurityFilter.class, AdminModulesController.class, AuthController.class})
@ActiveProfiles("test")
@WithMockUser(username = "testuser", roles = "ADMIN")
class AdminModulesControllerTest {
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

    @MockBean
    private AdminModulesService adminModulesService;

    @MockBean
    private AdminProfileAccessService adminProfileAccessService;

    @Test
    void saveModule() throws Exception {
        UUID id = UUID.randomUUID();
        AdminModules adminModules = new AdminModules(id, "ADMIN", new Date(), null);

        Mockito.when(adminModulesService.save(Mockito.any())).thenReturn(adminModules);

        mockMvc.perform(post("/admin/modules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adminModules)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.id").value(adminModules.getId().toString()))
                .andExpect(jsonPath("$.name").value("ADMIN"));

        Mockito.verify(adminModulesService, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void getById() throws Exception {
        UUID id = UUID.randomUUID();

        AdminModules adminModules = new AdminModules(id, "ADMIN", new Date(), null);


        Mockito.when(adminModulesService.findById(id)).thenReturn(Optional.of(adminModules));

        mockMvc.perform(get("/admin/module/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.id").value(adminModules.getId().toString()))
                .andExpect(jsonPath("$.name").value("ADMIN"));

        Mockito.verify(adminModulesService, Mockito.times(1)).findById(id);
    }

    @Test
    void deleteModule() throws Exception {
        UUID id = UUID.randomUUID();

        Mockito.when(adminModulesService.delete(id)).thenReturn(true);

        mockMvc.perform(delete("/admin/module/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(adminModulesService, Mockito.times(1)).delete(id);
    }

    @Test
    void update() throws Exception {
        UUID id = UUID.randomUUID();
        AdminModules adminModules = new AdminModules(id, "ADMIN UPDATED", new Date(), null);

        Mockito.when(adminModulesService.update(Mockito.any(), Mockito.any())).thenReturn(adminModules);

        mockMvc.perform(put("/admin/module/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adminModules)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.id").value(adminModules.getId().toString()))
                .andExpect(jsonPath("$.name").value("ADMIN UPDATED"));

        Mockito.verify(adminModulesService, Mockito.times(1)).update(Mockito.eq(id), Mockito.any());
    }
}