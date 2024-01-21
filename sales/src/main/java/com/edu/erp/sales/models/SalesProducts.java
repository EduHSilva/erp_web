package com.edu.erp.sales.models;

import com.edu.erp.sales.enums.StatusProduct;
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
@Table(name = "sales_products")
public class SalesProducts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private String img_url;

    private StatusProduct status;

    private Date dateCreated;

    private Date dateDeletion;
}
