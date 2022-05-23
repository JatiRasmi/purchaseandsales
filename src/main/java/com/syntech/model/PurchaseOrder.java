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
public class PurchaseOrder implements IEntity {

    private Long id;
    private Supplier supplierid;
    private String date;
    private String expecteddeliverydate;
    private PurchaseOrderDetail pod;

    public PurchaseOrder() {

    }

    public PurchaseOrder(Long id) {
        this.id = id;
    }

    public PurchaseOrder(Long id, Supplier supplierid, String date, String expecteddeliverydate,PurchaseOrderDetail pod) {
        this.id = id;
        this.supplierid = supplierid;
        this.date = date;
        this.expecteddeliverydate = expecteddeliverydate;
        this.pod = pod;
    }

    @Override
    public Long getId() {
        return id;
    }

    public PurchaseOrderDetail getPod() {
        return pod;
    }

    public void setPod(PurchaseOrderDetail pod) {
        this.pod = pod;
    }

    public Supplier getSupplierid() {
        return supplierid;
    }

    public String getDate() {
        return date;
    }

    public String getExpecteddeliverydate() {
        return expecteddeliverydate;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplierid(Supplier supplierid) {
        this.supplierid = supplierid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExpecteddeliverydate(String expecteddeliverydate) {
        this.expecteddeliverydate = expecteddeliverydate;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.supplierid);
        hash = 17 * hash + Objects.hashCode(this.date);
        hash = 17 * hash + Objects.hashCode(this.expecteddeliverydate);
        hash = 17 * hash + Objects.hashCode(this.pod);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PurchaseOrder)) {
            return false;
        }
        final PurchaseOrder other = (PurchaseOrder) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.expecteddeliverydate, other.expecteddeliverydate)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.supplierid, other.supplierid)) {
            return false;
        }
        if (!Objects.equals(this.pod, other.pod)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "\n id = " + id + "\n supplier : \t " + supplierid + " \n date = " + date + " \n expecteddeliverydate = " + expecteddeliverydate+ " \n totalsumamount = " + pod;
    }

}
