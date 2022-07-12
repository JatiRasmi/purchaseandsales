/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.PurchaseOrderDetail;
import com.syntech.repository.PurchaseOrderDetailRepository;
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
public class PurchaseOrderDetailController implements Serializable {

    private PurchaseOrderDetail purchaseOrderDetail;
    private List<PurchaseOrderDetail> purchaseOrderDetails;

    @Inject
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Inject
    MessageUtill messageUtill;

    public PurchaseOrderDetail getProductOrderDetail() {
        return purchaseOrderDetail;
    }

    public void setProductOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetail = purchaseOrderDetail;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetailList() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetailList(List<PurchaseOrderDetail> purchaseOrderDetailList) {
        this.purchaseOrderDetails = purchaseOrderDetailList;
    }

    @PostConstruct
    public void init() {
        this.purchaseOrderDetail = new PurchaseOrderDetail();
        this.purchaseOrderDetails = purchaseOrderDetailRepository.findAll();
    }

    public void beforeCreate() {
        this.purchaseOrderDetail = new PurchaseOrderDetail();
    }

    public void create() {
        purchaseOrderDetailRepository.create(purchaseOrderDetail);
        this.purchaseOrderDetails = purchaseOrderDetailRepository.findAll();
        messageUtill.showInfo("Order for purchase Added Successfully", "Order Added");
    }

    public void findAll() {
        purchaseOrderDetailRepository.findAll();
    }

    public void findById(Long id) {
        purchaseOrderDetailRepository.findById(id);
    }

    public void beforeEdit(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetail = purchaseOrderDetailRepository.findById(purchaseOrderDetail.getId());
    }

    public void edit() {
        purchaseOrderDetailRepository.edit(this.purchaseOrderDetail);
        this.purchaseOrderDetails = purchaseOrderDetailRepository.findAll();
        messageUtill.showInfo("Order for purchase Edited Successfully", "Order Edited");
    }

    public void delete(PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrderDetailRepository.delete(purchaseOrderDetail);
        purchaseOrderDetails = purchaseOrderDetailRepository.findAll();
        messageUtill.showInfo("Order for purchase Deleted Successfully", "Order Deleted" );
    }
}