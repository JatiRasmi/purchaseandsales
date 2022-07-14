/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rasmi
 */
@Entity
@Table(name = "purchaseorderdetail")
public class PurchaseOrderDetail implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false) 
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    @Column(name = "discount_amount", nullable = false)
    private BigDecimal discountamount;

    @Column(name = "sub_total_after_discount", nullable = false)
    private BigDecimal subtotalafterdiscount;

    @Column(name = "vat", nullable = false)
    private BigDecimal vat;

    @Column(name = "vat_amount", nullable = false)
    private BigDecimal vatamount;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalamount;

    public PurchaseOrderDetail() {

    }

    public PurchaseOrderDetail(Long id, PurchaseOrder purchaseOrder, Product product, BigDecimal quantity, BigDecimal rate, BigDecimal subtotal, BigDecimal discount, BigDecimal discountamount, BigDecimal subtotalafterdiscount, BigDecimal vat, BigDecimal vatamount, BigDecimal totalamount) {
        this.id = id;
        this.purchaseOrder = purchaseOrder;
        this.product = product;
        this.quantity = quantity;
        this.rate = rate;
        this.subtotal = subtotal;
        this.discount = discount;
        this.discountamount = discountamount;
        this.subtotalafterdiscount = subtotalafterdiscount;
        this.vat = vat;
        this.vatamount = vatamount;
        this.totalamount = totalamount;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountamount() {
        return discountamount;
    }

    public void setDiscountamount(BigDecimal discountamount) {
        this.discountamount = discountamount;
    }

    public BigDecimal getSubtotalafterdiscount() {
        return subtotalafterdiscount;
    }

    public void setSubtotalafterdiscount(BigDecimal subtotalafterdiscount) {
        this.subtotalafterdiscount = subtotalafterdiscount;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getVatamount() {
        return vatamount;
    }

    public void setVatamount(BigDecimal vatamount) {
        this.vatamount = vatamount;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    
    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.purchaseOrder);
        hash = 89 * hash + Objects.hashCode(this.product);
        hash = 89 * hash + Objects.hashCode(this.quantity);
        hash = 89 * hash + Objects.hashCode(this.rate);
        hash = 89 * hash + Objects.hashCode(this.subtotal);
        hash = 89 * hash + Objects.hashCode(this.discount);
        hash = 89 * hash + Objects.hashCode(this.vat);
        hash = 89 * hash + Objects.hashCode(this.totalamount);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PurchaseOrderDetail)) {
            return false;
        }
        final PurchaseOrderDetail other = (PurchaseOrderDetail) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.purchaseOrder, other.purchaseOrder)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.rate, other.rate)) {
            return false;
        }
        if (!Objects.equals(this.subtotal, other.subtotal)) {
            return false;
        }
        if (!Objects.equals(this.discount, other.discount)) {
            return false;
        }
        if (!Objects.equals(this.vat, other.vat)) {
            return false;
        }
        if (!Objects.equals(this.totalamount, other.totalamount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetail{" + "id=" + id + ", purchaseOrder=" + purchaseOrder + ", product=" + product + ", quantity=" + quantity + ", rate=" + rate + ", subtotal=" + subtotal + ", discount=" + discount + ", discountamount=" + discountamount + ", subtotalafterdiscount=" + subtotalafterdiscount + ", vat=" + vat + ", vatamount=" + vatamount + ", totalamount=" + totalamount + '}';
    }   
}
