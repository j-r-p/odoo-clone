package com.odoo.clone.backend.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
public class Contact extends BaseEntity {

    private String name;
    private String email;
    private String phone;
    private String address;
}
