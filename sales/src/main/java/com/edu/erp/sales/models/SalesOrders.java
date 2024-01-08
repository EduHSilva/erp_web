package com.edu.erp.sales.models;

import com.edu.erp.sales.enums.Status;
import com.edu.erp.sales.enums.StatusOrder;
import com.edu.erp.sales.enums.TypePerson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales_orders")
public class SalesOrders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Date dateCreated;

    private Date dateDeletion;

    private StatusOrder statusOrder;

    private Double commission;

    private Date dueDate;

    private Double total;

    @OneToOne
    private SalesPersons seller;

    @OneToOne
    private SalesPersons client;

    @OneToMany
    private List<SalesOrderItems> items;

}
