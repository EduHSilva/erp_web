package com.edu.erp.sales.models;

import com.edu.erp.sales.enums.Status;
import com.edu.erp.sales.enums.TypePerson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales_persons")
public class SalesPersons implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String cpf_cnpj;

    private Date dateCreated;

    private Status status;

    private TypePerson type;

    private Date dateDeletion;

    private String email;

    private String phone;

    @OneToOne
    private SalesCities city;
}
