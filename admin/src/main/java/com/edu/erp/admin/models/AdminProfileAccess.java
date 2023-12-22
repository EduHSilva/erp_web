package com.edu.erp.admin.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "admin_profile_access")
public class AdminProfileAccess implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Date dateCreated;

    private Date dateDeletion;

    @ManyToMany
    @JoinTable(name = "admin_profile_access_modules")
    private List<AdminModules> adminModules;
}
