package com.edu.erp.admin.dtos;

public record LoginResponseDTO(String token, String username,
                               java.util.List<com.edu.erp.admin.models.AdminModules> adminModules) {

}
