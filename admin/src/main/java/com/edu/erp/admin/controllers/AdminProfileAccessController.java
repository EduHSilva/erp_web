package com.edu.erp.admin.controllers;

import com.edu.erp.admin.dtos.AdminModuleDTO;
import com.edu.erp.admin.dtos.AdminProfileAccessDTO;
import com.edu.erp.admin.dtos.AdminProfileModuleDTO;
import com.edu.erp.admin.dtos.AdminUsersRecordDTO;
import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminProfileAccess;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.services.AdminModulesService;
import com.edu.erp.admin.services.AdminProfileAccessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@Tag(name = "Profiles")
@RequestMapping("/admin")
public class AdminProfileAccessController {
    private final AdminProfileAccessService service;

    public AdminProfileAccessController(AdminProfileAccessService adminProfileAccessService) {
        this.service = adminProfileAccessService;
    }

    @PostMapping("/profiles")
    public ResponseEntity<AdminProfileAccess> save(@RequestBody @Valid AdminProfileAccessDTO dto) {
        AdminProfileAccess adminProfileAccessModel = new AdminProfileAccess();
        BeanUtils.copyProperties(dto, adminProfileAccessModel);
        AdminProfileAccess adminProfileAccess = service.save(adminProfileAccessModel);

        return (adminProfileAccess != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(adminProfileAccess) :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("/profiles")
    public ResponseEntity<Page<AdminProfileAccess>> get(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<AdminProfileAccess> getById(@PathVariable UUID id) {
        Optional<AdminProfileAccess> profile = service.findById(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        return service.delete(id) ?
                ResponseEntity.ok("Deleted") :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid AdminProfileAccessDTO dto) {
        AdminProfileAccess profile = service.update(id, dto);
        return (profile != null) ?
                ResponseEntity.ok(profile) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/profile/module")
    public ResponseEntity<AdminProfileAccess> addModule(@RequestBody @Valid AdminProfileModuleDTO dto) {
        AdminProfileAccess profile = service.addModule(dto);
        return (profile != null) ?
                ResponseEntity.ok(profile) :
                ResponseEntity.notFound().build();
    }
}
