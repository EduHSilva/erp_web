package com.edu.erp.sales.enums;

import com.edu.erp.sales.dto.groups.CnpjGroup;
import com.edu.erp.sales.dto.groups.CpfGroup;
import lombok.Getter;

@Getter
public enum TypePerson {
   CLIENT_PJ("PJ", "CNPJ", "00.000.000/0000-00", CnpjGroup.class),
   CLIENT_PF("PF", "CPF", "000.000.000-00", CpfGroup.class),
   SELLER("PF", "CPF", "000.000.000-00", CpfGroup.class),;

   TypePerson(String description, String docType, String mask, Class<?> group) {
      this.description = description;
      this.docType = docType;
      this.mask = mask;
      this.group = group;
   }

   private final String description;
   private final String docType;
   private final String mask;
   private final Class<?> group;
}
