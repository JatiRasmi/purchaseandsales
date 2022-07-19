/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.DayBook;
import com.syntech.model.DayBookDetail;
import com.syntech.model.PurchaseOrder;
import com.syntech.model.SalesOrder;
import com.syntech.model.TransactionType;
import com.syntech.repository.PurchaseOrderRepository;
import com.syntech.repository.SalesOrderRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rasmi
 */
@Named
@ViewScoped
public class DayBookController implements Serializable {

    private DayBookDetail dayBookDetail;
    private DayBook dayBook;
    private LocalDate date;

    private List<PurchaseOrder> purchaseOrderList;
    private List<SalesOrder> salesOrderList;
    private List<DayBookDetail> dayBookDetailList;
    private List<DayBook> dayBookList;

    private PurchaseOrderController purchaseOrderController;
    private SalesOrderController salesOrderController;

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;
    @Inject
    private SalesOrderRepository salesOrderRepository;

    public DayBookController() {
    }

    
    
    public DayBook getDayBook() {
        return dayBook;
    }

    public void setDayBook(DayBook dayBook) {
        this.dayBook = dayBook;
    }

    public List<DayBook> getDayBookList() {
        return dayBookList;
    }

    public void setDayBookList(List<DayBook> dayBookList) {
        this.dayBookList = dayBookList;
    }

    public DayBookDetail getDayBookDetail() {
        return dayBookDetail;
    }

    public void setDayBookDetail(DayBookDetail dayBookDetail) {
        this.dayBookDetail = dayBookDetail;
    }

    public List<DayBookDetail> getDayBookDetailList() {
        return dayBookDetailList;
    }

    public void setDayBookDetailList(List<DayBookDetail> dayBookDetailList) {
        this.dayBookDetailList = dayBookDetailList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void preparedaybook() {
        purchaseOrderList = purchaseOrderRepository.findByDate(date);
        salesOrderList = salesOrderRepository.findByDate(date);
        BigDecimal todayBalance = BigDecimal.ZERO;
        
        dayBookDetailList = new ArrayList<>();
        
        for (PurchaseOrder po : purchaseOrderList) {
            dayBookDetail = new DayBookDetail(po.getSupplier().toString(), TransactionType.PURCHASE, BigDecimal.ZERO, po.getTotalAmount());
            dayBookDetailList.add(dayBookDetail);            
            todayBalance = todayBalance.subtract(po.getTotalAmount());
        }

        for (SalesOrder so : salesOrderList) {
            dayBookDetail = new DayBookDetail(so.getCustomer().toString(), TransactionType.SALE, so.getTotalAmount(), BigDecimal.ZERO);
            dayBookDetailList.add(dayBookDetail);            
            todayBalance = todayBalance.add(so.getTotalAmount());
        }
        
        BigDecimal openingBalance = salesOrderRepository.calculateTotalAmountBeforeDate(date)
                .subtract(purchaseOrderRepository.calculateTotalAmountBeforeDate(date));
        BigDecimal closingBalance = openingBalance.add(todayBalance);
        dayBook = new DayBook();
        dayBook.setTodayBalance(todayBalance);
        dayBook.setOpeningBalance(openingBalance);
        dayBook.setClosingBalance(closingBalance);
        dayBook.setTransactionDate(date);
    }
}
