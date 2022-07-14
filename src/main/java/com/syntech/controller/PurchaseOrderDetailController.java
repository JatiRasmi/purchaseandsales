/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.PurchaseOrder;
import com.syntech.util.CalculationUtill;
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
    private List<PurchaseOrderDetail> purchaseOrderDetailList;
    private PurchaseOrder purchaseOrder;

    @Inject
    private PurchaseOrderController purchaseOrderController;

    @Inject
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Inject
    MessageUtill messageUtill;

    @Inject
    CalculationUtill calculationUtill;

    public PurchaseOrderDetail getPurchaseOrderDetail() {
        return purchaseOrderDetail;
    }

    public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetail = purchaseOrderDetail;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetailList() {
        return purchaseOrderDetailList;
    }

    public void setPurchaseOrderDetailList(List<PurchaseOrderDetail> purchaseOrderDetailList) {
        this.purchaseOrderDetailList = purchaseOrderDetailList;
    }

    @PostConstruct
    public void init() {
        this.purchaseOrderDetail = new PurchaseOrderDetail();
        this.purchaseOrderDetailList = purchaseOrderDetailRepository.findAll();
    }

    public void beforeCreate() {
        this.purchaseOrderDetail = new PurchaseOrderDetail();
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
        this.purchaseOrderDetailList = purchaseOrderDetailRepository.findAll();
        messageUtill.showInfo("Order for purchase Edited Successfully", "Order Edited");
    }

    public void delete(PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrderDetailRepository.delete(purchaseOrderDetail);
        purchaseOrderDetailList = purchaseOrderDetailRepository.findAll();
        messageUtill.showInfo("Order for purchase Deleted Successfully", "Order Deleted");
    }
}
