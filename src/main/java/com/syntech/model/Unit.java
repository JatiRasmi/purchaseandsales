/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import java.util.Objects;
//import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author rasmi
 */
@Entity
@Table(name = "unit")
public class Unit implements IEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;
    
    @NotNull(message =  "Unit is mandatory")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Unit should be string")
    @Column(name = "name", unique = true)
    private String name;
    
//    private static final Logger logger = Logger.getLogger(Unit.class.getName());

    public Unit() {

    }

    public Unit(Long id) {
        this.id = id;
    }

    public Unit(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Unit)) {
            return false;
        }
        final Unit other = (Unit) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id=" + id + " Name=" + name;
    }

}
