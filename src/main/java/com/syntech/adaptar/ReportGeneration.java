/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.adaptar;

import com.syntech.model.DayBook;
import com.syntech.model.DayBookDetail;
import com.syntech.model.PurchaseOrder;
import com.syntech.model.SalesOrder;
import com.syntech.model.TransactionType;
import com.syntech.repository.PurchaseOrderRepository;
import com.syntech.repository.SalesOrderRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
public class ReportGeneration implements Serializable {

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    @Inject
    private SalesOrderRepository salesOrderRepository;

    public DayBook preparedaybook(Date date) {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findByDate(date);
        List<SalesOrder> salesOrderList = salesOrderRepository.findByDate(date);
        BigDecimal todayBalance = BigDecimal.ZERO;
        DayBook dayBook = new DayBook();
        dayBook.setDayBookDetailList(new ArrayList<>());

        for (PurchaseOrder po : purchaseOrderList) {
            DayBookDetail dayBookDetail = new DayBookDetail(po.getSupplier().getName(), TransactionType.PURCHASE, BigDecimal.ZERO, po.getTotalAmount());
            dayBook.getDayBookDetailList().add(dayBookDetail);
            todayBalance = todayBalance.subtract(po.getTotalAmount());
        }

        for (SalesOrder so : salesOrderList) {
            DayBookDetail dayBookDetail = new DayBookDetail(so.getCustomer().getName(), TransactionType.SALE, so.getTotalAmount(), BigDecimal.ZERO);
            dayBook.getDayBookDetailList().add(dayBookDetail);
            todayBalance = todayBalance.add(so.getTotalAmount());
        }

        BigDecimal openingBalance = salesOrderRepository.calculateTotalAmountBeforeDate(date)
                .subtract(purchaseOrderRepository.calculateTotalAmountBeforeDate(date));
        BigDecimal closingBalance = openingBalance.add(todayBalance);
        dayBook.setTodayBalance(todayBalance);
        dayBook.setOpeningBalance(openingBalance);
        dayBook.setClosingBalance(closingBalance);
        dayBook.setTransactionDate(date);
        return dayBook;
    }
}
