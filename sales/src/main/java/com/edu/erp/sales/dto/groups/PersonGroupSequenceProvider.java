package com.edu.erp.sales.dto.groups;

import com.edu.erp.sales.dto.SalesPersonDTO;
import com.edu.erp.sales.enums.TypePerson;
import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class PersonGroupSequenceProvider implements DefaultGroupSequenceProvider<SalesPersonDTO> {
    @Override
    public List<Class<?>> getValidationGroups(SalesPersonDTO salesPersonDTO) {
        List<Class<?>> groups = new ArrayList<>();

        groups.add(SalesPersonDTO.class);

        if (salesPersonDTO != null) {
            groups.add(SalesPersonDTO.class);

            if (salesPersonDTO.type() != null) {
                groups.add(salesPersonDTO.type().equals(TypePerson.CLIENT_PF) ? CpfGroup.class : CnpjGroup.class);
            }
        }

        return groups;
    }
}
