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

/**
 *
 * @author rasmi
 */
@Entity
@Table(name = "saesorderdetail")
public class SalesOrderDetail implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "sales_order_id" , nullable = false)
    private SalesOrder salesorder;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable =false)
    private Product product;
    
    @Column(name = "quantity" , nullable = false)
    private Long quantity;
    
    @Column(name = "rate" , nullable = false)
    private Long rate;
    
    @Column(name = "sub_total" , nullable = false)
    private Long subtotal;
    
    @Column(name = "discount" , nullable = false)
    private Long discount;
    
    @Column(name = "discount_amount" , nullable = false)
    private Long discountamount;
    
    @Column(name = "sub_total_after_discount" , nullable = false)
    private Long subtotalafterdiscount;
    
    @Column(name = "vat" , nullable = false)
    private Long vat;
    
    @Column(name = "vat_amount" , nullable = false)
    private Long vatamount;
    
    @Column(name = "total_amount" , nullable = false)
    private Long totalamount;

    public SalesOrderDetail() {

    }

    public SalesOrderDetail(Long id, SalesOrder salesorder, Product product, Long quantity, Long rate, Long subtotal, Long discount, Long discountamount, Long subtotalafterdiscount, Long vat, Long vatamount, Long totalamount) {
        this.id = id;
        this.salesorder = salesorder;
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

    public SalesOrderDetail(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public SalesOrder getSalesorder() {
        return salesorder;
    }

    public Product getProduct() {
        return product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getRate() {
        return rate;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public Long getDiscount() {
        return discount;
    }

    public Long getDiscountamount() {
        return discountamount;
    }

    public Long getSubtotalAfterDiscount() {
        return subtotalafterdiscount;
    }

    public Long getVat() {
        return vat;
    }

    public Long getVatamount() {
        return vatamount;
    }

    public Long getTotalamount() {
        return totalamount;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setSalesorder(SalesOrder salesorder) {
        this.salesorder = salesorder;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public void setVat(Long vat) {
        this.vat = vat;
    }

    public void setTotalamount(Long totalamount) {
        this.totalamount = totalamount;
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.salesorder);
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
        if (!(obj instanceof SalesOrderDetail)) {
            return false;
        }
        final SalesOrderDetail other = (SalesOrderDetail) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.salesorder, other.salesorder)) {
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
        return "\n Id = " + id + "\n" + "\n _____Sales Order_______  \t" + salesorder + "\n _______Product_______  \t" + product + "\n" + "\n Quantity = " + quantity + "\n Rate = " + rate + "\n Sub Total = " + subtotal + "\n Discount(%) = " + discount + "\t Discount Amount = " + discountamount + "\n subtotalafterdiscount = " + subtotalafterdiscount + "\n Vat (%) = " + vat + "\t Vat Amount = " + vatamount + "\n Total Amount = " + totalamount;
    }

}
