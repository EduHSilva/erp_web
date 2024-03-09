package com.edu.erp.sales.models;

import com.edu.erp.sales.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales_orders")
public class SalesOrders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreated;

    private Date dateDeletion;

    private StatusOrder statusOrder;

    private Double commission;

    private Date dueDate;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SalesPersons seller;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private SalesPersons client;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<SalesOrderItems> items;

}
