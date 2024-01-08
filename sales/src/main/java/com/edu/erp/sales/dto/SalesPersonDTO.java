package com.edu.erp.sales.dto;

import com.edu.erp.sales.dto.groups.CnpjGroup;
import com.edu.erp.sales.dto.groups.CpfGroup;
import com.edu.erp.sales.dto.groups.PersonGroupSequenceProvider;
import com.edu.erp.sales.enums.Status;
import com.edu.erp.sales.enums.TypePerson;
import com.edu.erp.sales.models.SalesCities;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

@GroupSequenceProvider(PersonGroupSequenceProvider.class)
public record SalesPersonDTO(
        @NotBlank String name,
        @NotBlank @CPF(groups = CpfGroup.class)
        @CNPJ(groups = CnpjGroup.class) String cpf_cnpj,
        Status status,
        @NotNull TypePerson type,
        @NotBlank @Email String email,
        @NotBlank String phone,
        @NotNull SalesCities city
) {
}

