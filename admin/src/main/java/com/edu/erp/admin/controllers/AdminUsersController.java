package com.edu.erp.admin.controllers;

import com.edu.erp.admin.dtos.AdminUsersRecordDTO;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.services.AdminUsersService;
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
@Tag(name = "Users")
@RequestMapping("/admin")
public class AdminUsersController {

    private final AdminUsersService service;

    public AdminUsersController(AdminUsersService adminUsersService) {
        this.service = adminUsersService;
    }

    @PostMapping("/users")
    public ResponseEntity<AdminUsers> save(@RequestBody @Valid AdminUsersRecordDTO userRecordDTO) {
        AdminUsers userModel = new AdminUsers();
        BeanUtils.copyProperties(userRecordDTO, userModel);
        AdminUsers user = service.save(userModel);

        return (user != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(user) :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("/users")
    public ResponseEntity<Page<AdminUsers>> get(Pageable pageable) {
        Page<AdminUsers> users = service.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AdminUsers> getById(@PathVariable UUID id) {
        Optional<AdminUsers> user = service.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        return service.delete(id) ?
                ResponseEntity.ok("Deleted") :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid AdminUsersRecordDTO dto) {
        AdminUsers user = service.update(id, dto);
        return (user != null) ?
                ResponseEntity.ok(user) :
                ResponseEntity.notFound().build();
    }
}
