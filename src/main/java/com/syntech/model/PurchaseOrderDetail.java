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
    private BigDecimal quantity = BigDecimal.ZERO;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate = BigDecimal.ZERO;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(name = "discount_amount", nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "sub_total_after_discount", nullable = false)
    private BigDecimal subTotalAfterDiscount = BigDecimal.ZERO;

    @Column(name = "vat", nullable = false)
    private BigDecimal vat = BigDecimal.ZERO;

    @Column(name = "vat_amount", nullable = false)
    private BigDecimal vatAmount = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public PurchaseOrderDetail() {

    }

    public PurchaseOrderDetail(Long id, PurchaseOrder purchaseOrder, Product product, BigDecimal quantity, BigDecimal rate, BigDecimal subTotal, BigDecimal discount, BigDecimal discountAmount, BigDecimal subTotalAfterDiscount, BigDecimal vat, BigDecimal vatAmount, BigDecimal totalAmount) {
        this.id = id;
        this.purchaseOrder = purchaseOrder;
        this.product = product;
        this.quantity = quantity;
        this.rate = rate;
        this.subTotal = subTotal;
        this.discount = discount;
        this.discountAmount = discountAmount;
        this.subTotalAfterDiscount = subTotalAfterDiscount;
        this.vat = vat;
        this.vatAmount = vatAmount;
        this.totalAmount = totalAmount;
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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getsubTotalAfterDiscount() {
        return subTotalAfterDiscount;
    }

    public void setsubTotalAfterDiscount(BigDecimal subTotalAfterDiscount) {
        this.subTotalAfterDiscount = subTotalAfterDiscount;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getSubTotalAfterDiscount() {
        return subTotalAfterDiscount;
    }

    public void setSubTotalAfterDiscount(BigDecimal subTotalAfterDiscount) {
        this.subTotalAfterDiscount = subTotalAfterDiscount;
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.purchaseOrder);
        hash = 89 * hash + Objects.hashCode(this.product);
        hash = 89 * hash + Objects.hashCode(this.quantity);
        hash = 89 * hash + Objects.hashCode(this.rate);
        hash = 89 * hash + Objects.hashCode(this.subTotal);
        hash = 89 * hash + Objects.hashCode(this.discount);
        hash = 89 * hash + Objects.hashCode(this.vat);
        hash = 89 * hash + Objects.hashCode(this.totalAmount);
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
        if (!Objects.equals(this.subTotal, other.subTotal)) {
            return false;
        }
        if (!Objects.equals(this.discount, other.discount)) {
            return false;
        }
        if (!Objects.equals(this.vat, other.vat)) {
            return false;
        }
        if (!Objects.equals(this.totalAmount, other.totalAmount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetail{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + ", rate=" + rate + ", subtotal=" + subTotal + ", discount=" + discount + ", discountamount=" + discountAmount + ", subtotalafterdiscount=" + subTotalAfterDiscount + ", vat=" + vat + ", vatamount=" + vatAmount + ", totalamount=" + totalAmount + '}';
    }
}
