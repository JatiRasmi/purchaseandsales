/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.adapter.ReportGeneration;
import com.syntech.model.DayBook;
import com.syntech.util.DateUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private DateUtil dateUtil;

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
    public void init() throws ParseException {
        date = dateUtil.removeTime(new Date());
        dayBook = reportGeneration.preparedaybook(date);
    }

    public void preparedaybook() {
        dayBook = reportGeneration.preparedaybook(date);
    }
}
