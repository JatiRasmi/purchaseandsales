/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author rasmi
 */
public class Product implements IEntity {

    private Long id;
    private Unit unitid;
    private String name;
    private String description;

    public Product() {

    }

    public Product(Long id, Unit unitid, String name, String description) {
        this.id = id;
        this.unitid = unitid;
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
    public Unit getUnitid() {
        return unitid;
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

    public void setUnitid(Unit unitid) {
        this.unitid = unitid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n Id=" + id + " \n Unit :" + unitid + "\n Name=" + name + "\n Description=" + description;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.unitid);
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
        if (!Objects.equals(this.unitid, other.unitid)) {
            return false;
        }
        return true;
    }
}
