package com.edu.erp.sales.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales_order_items")
public class SalesOrderItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer quantity;

    private Double priceUnit;

    private Double subTotal;

    @JsonIgnore
    @ManyToOne
    SalesOrders order;

    @ManyToOne
    SalesProducts product;
}
