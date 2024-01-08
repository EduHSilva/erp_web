package com.edu.erp.sales.dto.groups;

import com.edu.erp.sales.dto.SalesPersonDTO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class PersonGroupSequenceProvider implements DefaultGroupSequenceProvider<SalesPersonDTO> {
    @Override
    public List<Class<?>> getValidationGroups(SalesPersonDTO salesPersonDTO) {
        List<Class<?>> groups = new ArrayList<>();
        if (salesPersonDTO != null) {
            groups.add(SalesPersonDTO.class);
            if (salesPersonDTO.type() != null) {
                groups.add(salesPersonDTO.type().getClass());
            }
        }
        return groups;
    }
}
