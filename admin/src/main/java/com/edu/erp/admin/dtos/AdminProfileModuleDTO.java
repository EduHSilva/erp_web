package com.edu.erp.admin.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record AdminProfileModuleDTO(@NotNull UUID idProfile ,
                                  @NotNull UUID idModule) {

}
