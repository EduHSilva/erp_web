package com.edu.erp.admin.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "admin_modules")
public class AdminModules implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Date dateCreated;

    private Date dateDeletion;

    @ManyToMany
    @JoinTable(name = "admin_profile_access_modules")
    private List<AdminProfileAccess> profileAccessList;
}
