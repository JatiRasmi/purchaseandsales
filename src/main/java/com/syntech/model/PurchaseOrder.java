/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Ordered Date should not be null")
    @Temporal(value = TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Ordered Date should not be null")
    @Temporal(value = TemporalType.DATE)
    @Column(name = "expected_delivery_date", nullable = false)
    private Date expectedDeliveryDate;

    @Column(nullable = false)
    private BigDecimal subTotal = BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal vatAmount = BigDecimal.ZERO;
    
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;


    @JsonManagedReference
//    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "purchaseOrder")  //mappedBy = purchaseOrder --> must be same as PurchaseOrderDetail's purchaseOrder variable
    private List<PurchaseOrderDetail> purchaseOrderDetailList;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Long id, Supplier supplier, Date date, Date expectedDeliveryDate, List<PurchaseOrderDetail> purchaseOrderDetailList) {
        this.id = id;
        this.supplier = supplier;
        this.date = date;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.purchaseOrderDetailList = purchaseOrderDetailList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetailList() {
        return purchaseOrderDetailList;
    }

    public void setPurchaseOrderDetailList(List<PurchaseOrderDetail> purchaseOrderDetails) {
        this.purchaseOrderDetailList = purchaseOrderDetails;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.supplier);
        hash = 83 * hash + Objects.hashCode(this.date);
        hash = 83 * hash + Objects.hashCode(this.expectedDeliveryDate);
        hash = 83 * hash + Objects.hashCode(this.totalAmount);
        hash = 83 * hash + Objects.hashCode(this.purchaseOrderDetailList);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PurchaseOrder)) {
            return false;
        }
        final PurchaseOrder other = (PurchaseOrder) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.expectedDeliveryDate, other.expectedDeliveryDate)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.supplier, other.supplier)) {
            return false;
        }
        if (!Objects.equals(this.totalAmount, other.totalAmount)) {
            return false;
        }
        if (!Objects.equals(this.purchaseOrderDetailList, other.purchaseOrderDetailList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "id=" + id + ", supplier=" + supplier + ", date=" + date + ", expecteddeliverydate=" + expectedDeliveryDate + ", totalamount=" + totalAmount + ", purchaseOrderDetails=" + purchaseOrderDetailList + '}';
    }

}
