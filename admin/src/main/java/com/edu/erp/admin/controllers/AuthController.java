package com.edu.erp.admin.controllers;

import com.edu.erp.admin.config.security.TokenService;
import com.edu.erp.admin.dtos.AuthDTO;
import com.edu.erp.admin.dtos.LoginResponseDTO;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminUsersRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Tag(name = "Auth")
@CrossOrigin("*")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final AdminUsersRepository repository;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, AdminUsersRepository repository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO dto) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((AdminUsers) auth.getPrincipal());
        UserDetails user = repository.findByEmail(dto.email());
        return ResponseEntity.ok(new LoginResponseDTO(token, user.getUsername()));
    }
}
