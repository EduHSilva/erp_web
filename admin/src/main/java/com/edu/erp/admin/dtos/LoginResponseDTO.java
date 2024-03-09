package com.edu.erp.admin.dtos;

import com.edu.erp.admin.enums.Language;

public record LoginResponseDTO(String token, String username,
                               java.util.List<com.edu.erp.admin.models.AdminModules> adminModules, Language language) {

}
