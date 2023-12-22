package com.edu.erp.admin.dtos;

import com.edu.erp.admin.enums.Language;
import com.edu.erp.admin.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record LoginResponseDTO(String token) {

}
