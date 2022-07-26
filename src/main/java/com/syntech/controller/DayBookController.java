/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syntech.adapter.ReportGeneration;
import com.syntech.model.DayBook;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
        dayBook = reportGeneration.preparedaybook(date);
    }

    public void preparedaybook() {
        dayBook = reportGeneration.preparedaybook(date);
    }
}
