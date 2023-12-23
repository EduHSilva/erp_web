package com.edu.erp.admin.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;

public record AdminModuleDTO(@NotBlank String name ,
                                 Date dateCreated,
                                 Date dateDeletion) {

    public AdminModuleDTO(String name) {
        this(name, new Date(), null);
    }
}
