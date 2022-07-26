/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rasmi
 */
public class DayBook {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(value = TemporalType.DATE)
    
    private Date transactionDate;
    private BigDecimal openingBalance;
    private BigDecimal closingBalance;
    private BigDecimal todayBalance;
    private List<DayBookDetail> dayBookDetailList;

    public DayBook() {
    }

    public DayBook(Date transactionDate, BigDecimal openingBalance, BigDecimal closingBalance, BigDecimal todayBalance) {
        this.transactionDate = transactionDate;
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        this.todayBalance = todayBalance;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public BigDecimal getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(BigDecimal closingBalance) {
        this.closingBalance = closingBalance;
    }

    public BigDecimal getTodayBalance() {
        return todayBalance;
    }

    public void setTodayBalance(BigDecimal todayBalance) {
        this.todayBalance = todayBalance;
    }

    public List<DayBookDetail> getDayBookDetailList() {
        return dayBookDetailList;
    }

    public void setDayBookDetailList(List<DayBookDetail> dayBookDetailList) {
        this.dayBookDetailList = dayBookDetailList;
    }

    
    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.transactionDate);
        hash = 37 * hash + Objects.hashCode(this.openingBalance);
        hash = 37 * hash + Objects.hashCode(this.closingBalance);
        hash = 37 * hash + Objects.hashCode(this.todayBalance);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DayBook)) {
            return false;
        }
        final DayBook other = (DayBook) obj;
        if (!Objects.equals(this.transactionDate, other.transactionDate)) {
            return false;
        }
        if (!Objects.equals(this.openingBalance, other.openingBalance)) {
            return false;
        }
        if (!Objects.equals(this.closingBalance, other.closingBalance)) {
            return false;
        }
        if (!Objects.equals(this.todayBalance, other.todayBalance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DayBook{" + "transactionDate=" + transactionDate + ", openingBalance=" + openingBalance + ", closingBalance=" + closingBalance + ", todayBalance=" + todayBalance + '}';
    }

}
