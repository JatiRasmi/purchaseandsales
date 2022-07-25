/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.adaptar.ReportGeneration;
import com.syntech.model.DayBook;
import com.syntech.model.PurchaseOrder;
import com.syntech.repository.PurchaseOrderRepository;
import com.syntech.repository.SalesOrderRepository;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
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
    private Date date;

    @Inject
    private ReportGeneration reportGeneration;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @PostConstruct
    public void init() {
        date = new Date();
    }

    public void preparedaybook() {
        dayBook = reportGeneration.preparedaybook(date);
    }
}
