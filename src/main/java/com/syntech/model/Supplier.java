/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *
 * @author rasmi
 */
@Entity
@Table(name = "supplier")
public class Supplier implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, length = 25, unique = true)
    private String email;

    @NotBlank
    @Positive
    @Size
    @Column(name = "contact", nullable = false, length = 12, unique = true)
    private String contact;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    public Supplier() {

    }

    public Supplier(Long id) {
        this.id = id;
    }

    public Supplier(Long id, String name, String address, String email, String contact, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.address);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.contact);
        hash = 53 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Supplier)) {
            return false;
        }
        final Supplier other = (Supplier) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id=" + id + "\t  name=" + name + " \t address=" + address + " \t email=" + email + " \t contact=" + contact + " \t description=" + description;
    }

}
