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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class DayBookController {

    private static PurchaseOrderRepository purchaseorderRepository;
    private static SalesOrderRepository salesorderRepository;
    private static PurchaseOrder purchaseorder;
    private static SalesOrder salesorder;

    public void dayBookOption(PurchaseOrderRepository purchaseorderRepository, SalesOrderRepository salesorderRepository) {
        this.purchaseorderRepository = purchaseorderRepository;
        this.salesorderRepository = salesorderRepository;

        Scanner sc = new Scanner(System.in);
        String choice;
        String date;
        do {
            System.out.println("----------------------Day Book Operations--------------------------");
            System.out.println("Enter 1 to list : ");
            System.out.println("Enter 2 to : ");
            System.out.println("Enter 3 to exit");
            System.out.println("-------------------------------------------------------------------");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("Enter date:");
                    date = sc.next();
                    DayBook db = preparedaybook(date);
                    System.out.println("Daybook" + db);
                    break;
                case "2":
//                    
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid Option!!");
                    break;
            }
            System.out.println();
        } while (!choice.equals("0"));
    }

    public DayBook preparedaybook(String date) {
        purchaseorderRepository = new PurchaseOrderRepository();
        salesorderRepository = new SalesOrderRepository();
        List<PurchaseOrder> polist = purchaseorderRepository.findByDate(date);
        List<SalesOrder> solist = salesorderRepository.findByDate(date);
        BigDecimal todayBalance = BigDecimal.ZERO;

        List<DayBookDetail> dayBookDetails = new ArrayList<>();
        for (PurchaseOrder po : polist) {
            DayBookDetail dbd = new DayBookDetail(po.getSupplierid().toString(), TransactionType.PURCHASE, BigDecimal.ZERO, po.getTotalAmount());
            dayBookDetails.add(dbd);
            todayBalance = todayBalance.subtract(po.getTotalAmount());
        }

        for (SalesOrder so : solist) {
            DayBookDetail dbd = new DayBookDetail(so.getCustomer().toString(), TransactionType.SALE, so.getTotalAmount(), BigDecimal.ZERO);
            dayBookDetails.add(dbd);
            todayBalance = todayBalance.add(so.getTotalAmount());
        }

        System.out.println(dayBookDetails);

        BigDecimal openingBalancce = salesorderRepository.CalculateTotalAmountBeforeDate(date)
                .subtract(purchaseorderRepository.CalculateTotalAmountBeforeDate(date));
        BigDecimal closingBalance = openingBalancce.add(todayBalance);
        DayBook db = new DayBook();
        db.setTransactionDate(date);
        db.setTodayBalance(todayBalance);
        db.setOpeningBalance(openingBalancce);
        db.setClosingBalance(closingBalance);
        return db;
    }
}
