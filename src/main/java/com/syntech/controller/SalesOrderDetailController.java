/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.SalesOrderDetail;
import com.syntech.repository.SalesOrderDetailRepository;
import com.syntech.util.MessageUtill;
import java.io.Serializable;
import java.util.List;
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
public class SalesOrderDetailController implements Serializable {

    private SalesOrderDetail salesOrderDetail;
    private List<SalesOrderDetail> salesOrderDetails;

    @Inject
    private SalesOrderDetailRepository salesOrderDetailRepository;

    @Inject
    MessageUtill messageUtill;

    public SalesOrderDetail getSalesOrderDetail() {
        return salesOrderDetail;
    }

    public void setSalesOrderDetail(SalesOrderDetail salesOrderDetail) {
        this.salesOrderDetail = salesOrderDetail;
    }

    public List<SalesOrderDetail> getSalesOrderDetails() {
        return salesOrderDetails;
    }

    public void setSalesOrderDetails(List<SalesOrderDetail> salesOrderDetails) {
        this.salesOrderDetails = salesOrderDetails;
    }

    

    @PostConstruct
    public void init() {
        this.salesOrderDetail = new SalesOrderDetail();
        this.salesOrderDetails = salesOrderDetailRepository.findAll();
    }

    public void beforeCreate() {
        this.salesOrderDetail = new SalesOrderDetail();
    }

    public void create() {
        salesOrderDetailRepository.create(salesOrderDetail);
        this.salesOrderDetails = salesOrderDetailRepository.findAll();
        messageUtill.showInfo("Order for Sales Added Successfully", "Order Added");
    }

    public void findAll() {
        salesOrderDetailRepository.findAll();
    }

    public void findById(Long id) {
        salesOrderDetailRepository.findById(id);
    }

    public void beforeEdit(SalesOrderDetail salesOrderDetail) {
        this.salesOrderDetail = salesOrderDetailRepository.findById(salesOrderDetail.getId());
    }

    public void edit() {
        salesOrderDetailRepository.edit(this.salesOrderDetail);
        this.salesOrderDetails = salesOrderDetailRepository.findAll();
        messageUtill.showInfo("Order for SalesEdited Successfully", "Order Edited");
    }

    public void delete(SalesOrderDetail salesOrderDetail) {
        salesOrderDetailRepository.delete(salesOrderDetail);
        salesOrderDetails = salesOrderDetailRepository.findAll();
        messageUtill.showInfo("Order for sales Deleted Successfully", "Order Deleted" );
    }
} 