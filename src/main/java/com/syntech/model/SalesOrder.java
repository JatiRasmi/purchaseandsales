/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author rasmi
 */
public class SalesOrder implements IEntity {

    private Long id;
    private Customer customer;
    private String date;
    private BigDecimal totalamount;

    public SalesOrder(Long id, Customer customer, String date, BigDecimal totalamount) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.totalamount = totalamount;
    }

    public void setCustomer(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalamount;
    }

    public void setTotalAmount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public SalesOrder(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public SalesOrder() {

    }

    public SalesOrder(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDate() {
        return date;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.customer);
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.totalamount);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof SalesOrder)) {
            return false;
        }
        final SalesOrder other = (SalesOrder) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.totalamount, other.totalamount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n id = " + id + "\ncustomer : \t" + customer + "\n date=" + date + "\n totalamount=" + totalamount;
    }

}
