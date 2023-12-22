package com.edu.erp.admin.controllers;

import com.edu.erp.admin.dtos.AdminUsersRecordDTO;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.services.AdminUsersService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminUsersController {

    private final AdminUsersService adminUsersService;

    public AdminUsersController(AdminUsersService adminUsersService) {
        this.adminUsersService = adminUsersService;
    }

    @PostMapping("/users")
    public ResponseEntity<AdminUsers> saveUser(@RequestBody @Valid AdminUsersRecordDTO userRecordDTO) {
        AdminUsers userModel = new AdminUsers();
        BeanUtils.copyProperties(userRecordDTO, userModel);
        AdminUsers user = adminUsersService.saveUser(userModel);

        return (user != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(user) :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUsers>> getUsers() {
        List<AdminUsers> users = adminUsersService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AdminUsers> getUserById(@PathVariable UUID id) {
        Optional<AdminUsers> user = adminUsersService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        return adminUsersService.deleteUser(id) ?
                ResponseEntity.ok("Deleted") :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody @Valid AdminUsersRecordDTO dto) {
        AdminUsers user = adminUsersService.updateUser(id, dto);
        return (user != null) ?
                ResponseEntity.ok(user) :
                ResponseEntity.notFound().build();
    }
}
