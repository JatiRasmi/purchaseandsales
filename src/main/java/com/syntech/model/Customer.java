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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *
 * @author rasmi
 */
@Entity
@Table(name = "customer")
public class Customer implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Name should not be null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name should be string")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull(message = "Address should not be null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Address should be string")
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @NotNull(message = "Email should not be null")
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+$", message = "Email is invalid")
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Positive
    @NotNull(message = "Contact number should not be null")
    @Size(max = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number should be 10 digits number")
    @Column(name = "contact", nullable = false, unique = true)
    private String contact;

    public Customer() {

    }

    public Customer(Long id, String name, String address, String email, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
    }

    public Customer(Long id) {
        this.id = id;
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

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.address);
        hash = 19 * hash + Objects.hashCode(this.email);
        hash = 19 * hash + Objects.hashCode(this.contact);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        final Customer other = (Customer) obj;
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id=" + id + ", Name=" + name + ", Address=" + address + ", Email=" + email + ", Contact_Number=" + contact;
    }

}
