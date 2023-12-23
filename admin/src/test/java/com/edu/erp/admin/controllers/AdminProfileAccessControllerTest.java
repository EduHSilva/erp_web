package com.edu.erp.admin.controllers;

import com.edu.erp.admin.config.security.SecurityFilter;
import com.edu.erp.admin.config.security.TokenService;
import com.edu.erp.admin.dtos.AdminProfileModuleDTO;
import com.edu.erp.admin.enums.Language;
import com.edu.erp.admin.enums.Status;
import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminProfileAccess;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class, controllers = AdminProfileAccessController.class)
@Import({SecurityFilter.class, AdminProfileAccessController.class, AuthController.class})
@ActiveProfiles("test")
@WithMockUser(username = "testuser", roles = "ADMIN")
class AdminProfileAccessControllerTest {
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
    void saveProfile() throws Exception {
        UUID id = UUID.randomUUID();
        AdminProfileAccess adminProfileAccess = new AdminProfileAccess(id, "ADMIN", new Date(), null, null);

        Mockito.when(adminProfileAccessService.save(Mockito.any())).thenReturn(adminProfileAccess);

        mockMvc.perform(post("/admin/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adminProfileAccess)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(adminProfileAccess.getId().toString()))
                .andExpect(jsonPath("$.name").value("ADMIN"));

        Mockito.verify(adminProfileAccessService, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void getById() throws Exception {
        UUID id = UUID.randomUUID();

        AdminProfileAccess adminProfileAccess = new AdminProfileAccess(id, "ADMIN", new Date(), null, null);


        Mockito.when(adminProfileAccessService.findById(id)).thenReturn(Optional.of(adminProfileAccess));

        mockMvc.perform(get("/admin/profile/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(adminProfileAccess.getId().toString()))
                .andExpect(jsonPath("$.name").value("ADMIN"));

        Mockito.verify(adminProfileAccessService, Mockito.times(1)).findById(id);
    }

    @Test
    void deleteModule() throws Exception {
        UUID id = UUID.randomUUID();

        Mockito.when(adminProfileAccessService.delete(id)).thenReturn(true);

        mockMvc.perform(delete("/admin/profile/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(adminProfileAccessService, Mockito.times(1)).delete(id);
    }

    @Test
    void update() throws Exception {
        UUID id = UUID.randomUUID();
        AdminProfileAccess adminProfileAccess = new AdminProfileAccess(id, "ADMIN UPDATED", new Date(), null, null);

        Mockito.when(adminProfileAccessService.update(Mockito.any(), Mockito.any())).thenReturn(adminProfileAccess);

        mockMvc.perform(put("/admin/profile/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adminProfileAccess)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(adminProfileAccess.getId().toString()))
                .andExpect(jsonPath("$.name").value("ADMIN UPDATED"));

        Mockito.verify(adminProfileAccessService, Mockito.times(1)).update(Mockito.eq(id), Mockito.any());
    }

    @Test
    void addModule() throws Exception {
        UUID idProfile = UUID.randomUUID();
        UUID idModule = UUID.randomUUID();
        AdminModules adminModules = new AdminModules(idModule, "ADMIN", new Date(), null);
        List<AdminModules> modulesList = new ArrayList<>();
        modulesList.add(adminModules);
        AdminProfileAccess adminProfileAccess = new AdminProfileAccess(idProfile, "ADMIN", new Date(), null, modulesList);

        AdminProfileModuleDTO dto = new AdminProfileModuleDTO(idProfile, idModule);

        Mockito.when(adminProfileAccessService.addModule(Mockito.any())).thenReturn(adminProfileAccess);

        mockMvc.perform(post("/admin/profile/module").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(adminProfileAccess.getId().toString()))
                .andExpect(jsonPath("$.adminModules[0].id").value(idModule.toString()));

        Mockito.verify(adminProfileAccessService, Mockito.times(1)).addModule(Mockito.any());
    }

}