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

    private DayBook dayBook;
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void preparedaybook() {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findByDate(date);
        List<SalesOrder> salesOrderList = salesOrderRepository.findByDate(date);
        BigDecimal todayBalance = BigDecimal.ZERO;
        dayBook = new DayBook();
        List<DayBookDetail> dayBookDetails = purchaseOrderRepository.findDayBookDetail(date);
        dayBook.setDayBookDetailList(new ArrayList<>());

        todayBalance = dayBookDetails.stream()
                .map(x -> x.getMoneyout()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b)).negate();

        
        System.out.println("today balance" + todayBalance);
//        for (PurchaseOrder po : purchaseOrderList) {
//            DayBookDetail dayBookDetail = new DayBookDetail(po.getSupplier().getName(), TransactionType.PURCHASE, BigDecimal.ZERO, po.getTotalAmount());
//            dayBook.getDayBookDetailList().add(dayBookDetail);
//            todayBalance = todayBalance.subtract(po.getTotalAmount());
//        }
//
//        for (SalesOrder so : salesOrderList) {
//            DayBookDetail dayBookDetail = new DayBookDetail(so.getCustomer().getName(), TransactionType.SALE, so.getTotalAmount(), BigDecimal.ZERO);
//            dayBook.getDayBookDetailList().add(dayBookDetail);
//            todayBalance = todayBalance.add(so.getTotalAmount());
//        }
//
//        BigDecimal openingBalance = salesOrderRepository.calculateTotalAmountBeforeDate(date)
//                .subtract(purchaseOrderRepository.calculateTotalAmountBeforeDate(date));
//        BigDecimal closingBalance = openingBalance.add(todayBalance);
//        dayBook.setTodayBalance(todayBalance);
//        dayBook.setOpeningBalance(openingBalance);
//        dayBook.setClosingBalance(closingBalance);
//        dayBook.setTransactionDate(date);
    }
}
