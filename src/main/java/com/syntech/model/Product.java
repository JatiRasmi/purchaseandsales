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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author rasmi
 */
@Entity
@Table(name = "product")
public class Product implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;
   
    @NotNull(message = "Name should not be null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name should be string")
    @Column(name = "name" ,unique = true, nullable = false , length = 50)
    private String name;
    
    @Column(name = "description", length = 50)
    private String description;

    public Product() {

    }

    public Product(Long id) {
        this.id = id;
    }


    public Product(Long id, Unit unitid, String name, String description) {
        this.id = id;
        this.unit = unitid;
        this.name = name;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public Unit getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setUnit(Unit unitid) {
        this.unit = unitid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.unit);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Product)) {
            return false;
        }

        final Product other = (Product) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n Id=" + id + " \n Unit :" + unit + "\n Name=" + name + "\n Description=" + description;
    }

}
