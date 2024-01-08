package com.edu.erp.sales.models;

import com.edu.erp.sales.enums.StatusOrder;
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
@Table(name = "sales_order_items")
public class SalesOrderItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer quantity;

    private Double priceUnit;

    private Double subTotal;

    @OneToOne
    SalesOrders order;

    @ManyToOne
    SalesProducts product;
}
