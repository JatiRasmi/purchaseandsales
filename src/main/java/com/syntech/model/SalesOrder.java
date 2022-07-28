/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "sales_order")
public class SalesOrder implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Ordered Date should not be null")
    @Temporal(value = TemporalType.DATE)
    @Column(name = "date", nullable = false, length = 20)
    private Date date = new Date();

    
    @Column(nullable = false)
    private BigDecimal subTotal = BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal vatAmount = BigDecimal.ZERO;

    @NotNull(message = "total amount should not be null")
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    
    @JsonManagedReference
//    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "salesOrder")
    private List<SalesOrderDetail> salesOrderDetailList;

    public SalesOrder(Long id, Customer customer, Date date, BigDecimal totalamount) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.totalAmount = totalamount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SalesOrder(BigDecimal totalamount) {
        this.totalAmount = totalamount;
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

    public Date getDate() {
        return date;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalamount) {
        this.totalAmount = totalamount;
    }

    public List<SalesOrderDetail> getSalesOrderDetailList() {
        return salesOrderDetailList;
    }

    public void setSalesOrderDetailList(List<SalesOrderDetail> salesOrderDetailList) {
        this.salesOrderDetailList = salesOrderDetailList;
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
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.customer);
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.totalAmount);
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
        if (!Objects.equals(this.totalAmount, other.totalAmount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n id = " + id + "\ncustomer : \t" + customer + "\n date=" + date + "\n totalamount=" + totalAmount;
    }

}
