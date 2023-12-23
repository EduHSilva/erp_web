package com.edu.erp.admin.controllers;

import com.edu.erp.admin.config.security.TokenService;
import com.edu.erp.admin.dtos.AuthDTO;
import com.edu.erp.admin.dtos.LoginResponseDTO;
import com.edu.erp.admin.models.AdminUsers;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO dto) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((AdminUsers) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
