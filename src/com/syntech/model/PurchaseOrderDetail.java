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
public class PurchaseOrderDetail implements IEntity {
    private Long id;
    private PurchaseOrder purchaseorder;
    private Product name;
    private Long quantity;
    private Long rate;
    private Long subtotal;
    private Long discount;
    private Long discountamount;
    private Long subtotalafterdiscount;
    private Long vat;
    private Long vatamount;
    private Long totalamount;

    public PurchaseOrderDetail() {
        
    }
    

    public PurchaseOrderDetail(Long id, PurchaseOrder purchaseorder, Product name, Long quantity, Long rate, Long subtotal, Long discount, Long discountamount,Long subtotalafterdiscount, Long vat, Long vatamount, Long totalamount) {
        this.id = id;
        this.purchaseorder = purchaseorder;
        this.name = name;
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

    public PurchaseOrder getPurchaseorder() {
        return purchaseorder;
    }

    public Product getName() {
        return name;
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

    public Long getVat() {
        return vat;
    }

    public Long getTotalamount() {
        return totalamount;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setPurchaseorder(PurchaseOrder purchaseorder) {
        this.purchaseorder = purchaseorder;
    }

    public void setName(Product name) {
        this.name = name;
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
        hash = 89 * hash + Objects.hashCode(this.purchaseorder);
        hash = 89 * hash + Objects.hashCode(this.name);
//        hash = 89 * hash + Objects.hashCode(this.unit);
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
        if (!Objects.equals(this.purchaseorder, other.purchaseorder)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
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
        return "\n Id = " + id + "\n ----Purchase Order-------  \t" + purchaseorder + "\n ---------Product----------  \t" + "--------------------"+ name + "\n Quantity = " + quantity + "\n Rate = " + rate + "\n Sub Total = " + subtotal + "\n Discount(%) = " + discount +"\t Discount Amount = " + discountamount +"\n subtotalafterdiscount = "+ subtotalafterdiscount + "\n Vat (%) = " + vat + "\t Vat Amount = " + vatamount + "\n Total Amount = " + totalamount;
    }
    
}
