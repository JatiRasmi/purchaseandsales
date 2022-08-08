/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import java.math.BigDecimal;
//import java.util.List;
import java.util.Objects;

/**
 *
 * @author rasmi
 */
public class DayBookDetail {

    private String customerSupplierName;
    private TransactionType transactiontype;
    private BigDecimal moneyin;
    private BigDecimal moneyout;
    public DayBookDetail() {
    }

    public DayBookDetail(String customerSupplierName, BigDecimal moneyout) {
        this.customerSupplierName = customerSupplierName;
        this.moneyout = moneyout;
    }

    public DayBookDetail(String customerSupplierName, TransactionType transactiontype, BigDecimal moneyin, BigDecimal moneyout) {
        this.customerSupplierName = customerSupplierName;
        this.transactiontype = transactiontype;
        this.moneyin = moneyin;
        this.moneyout = moneyout;
    }


    public DayBookDetail(BigDecimal moneyout) {
        this.moneyout = moneyout;
    }

    public String getCustomerSupplierName() {
        return customerSupplierName;
    }

    public void setCustomerSupplierName(String customerSupplierName) {
        this.customerSupplierName = customerSupplierName;
    }

    public TransactionType getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(TransactionType transactiontype) {
        this.transactiontype = transactiontype;
    }

    public BigDecimal getMoneyin() {
        return moneyin.setScale(2, BigDecimal.ROUND_UP);
    }

    public void setMoneyin(BigDecimal moneyin) {
        this.moneyin = moneyin;
    }

    public BigDecimal getMoneyout() {
        return moneyout.setScale(2, BigDecimal.ROUND_UP);
    }

    public void setMoneyout(BigDecimal moneyout) {
        this.moneyout = moneyout;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.customerSupplierName);
        hash = 11 * hash + Objects.hashCode(this.transactiontype);
        hash = 11 * hash + Objects.hashCode(this.moneyin);
        hash = 11 * hash + Objects.hashCode(this.moneyout);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DayBookDetail)) {
            return false;
        }
        final DayBookDetail other = (DayBookDetail) obj;
        if (!Objects.equals(this.customerSupplierName, other.customerSupplierName)) {
            return false;
        }
        if (this.transactiontype != other.transactiontype) {
            return false;
        }
        if (!Objects.equals(this.moneyin, other.moneyin)) {
            return false;
        }
        if (!Objects.equals(this.moneyout, other.moneyout)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DayBookDetail{" + "customerSupplierName=" + customerSupplierName +
                ", transactiontype=" + transactiontype + ", moneyin=" + moneyin +
                ", moneyout=" + moneyout + '}';
    }

}
