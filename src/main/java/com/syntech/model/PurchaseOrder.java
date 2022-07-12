/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rasmi
 */
@Entity
@Table(name = "purchaseorder")
public class PurchaseOrder implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //auto generate id
    @Column(name = " id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "expected_delivery_date", nullable = false)
    private String expecteddeliverydate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalamount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "purchaseOrder")  //mappedBy = purchaseOrder --> must be same as PurchaseOrderDetail's purchaseOrder variable
    private List<PurchaseOrderDetail> purchaseOrderDetails;

    public PurchaseOrder() {

    }

    public PurchaseOrder(Long id) {
        this.id = id;
    }

    public PurchaseOrder(Long id, Supplier supplier, String date, String expecteddeliverydate, BigDecimal totalamount) {
        this.id = id;
        this.supplier = supplier;
        this.date = date;
        this.expecteddeliverydate = expecteddeliverydate;
        this.totalamount = totalamount;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setSupplier(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalamount;
    }

    public void setTotalAmount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public Supplier getSupplier() {
        return supplier;
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

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
        hash = 17 * hash + Objects.hashCode(this.supplier);
        hash = 17 * hash + Objects.hashCode(this.date);
        hash = 17 * hash + Objects.hashCode(this.expecteddeliverydate);
        hash = 17 * hash + Objects.hashCode(this.totalamount);
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
        if (!Objects.equals(this.supplier, other.supplier)) {
            return false;
        }
        if (!Objects.equals(this.totalamount, other.totalamount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n id = " + id + "\n supplier : \t " + supplier + " \n date = " + date + " \n expecteddeliverydate = " + expecteddeliverydate + " \n totalsumamount = " + totalamount;
    }

}
