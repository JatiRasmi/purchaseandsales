/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.PurchaseOrder;
import com.syntech.repository.PurchaseOrderRepository;
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
public class PurchaseOrderController implements Serializable {

    private PurchaseOrder purchaseOrder;
    private List<PurchaseOrder> purchaseOrderList;

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    @Inject
    MessageUtill messageUtill;

    public PurchaseOrder getProductOrder() {
        return purchaseOrder;
    }

    public void setProductOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public List<PurchaseOrder> getPurchaseOrderList() {
        return purchaseOrderList;
    }

    public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
        this.purchaseOrderList = purchaseOrderList;
    }

    @PostConstruct
    public void init() {
        this.purchaseOrder = new PurchaseOrder();
        this.purchaseOrderList = purchaseOrderRepository.findAll();
    }

    public void beforeCreate() {
        this.purchaseOrder = new PurchaseOrder();
    }

    public void create() {
        purchaseOrderRepository.create(purchaseOrder);
        this.purchaseOrderList = purchaseOrderRepository.findAll();
        messageUtill.showInfo("Order for purchase Added Successfully", "Order Added");
    }

    public void findAll() {
        purchaseOrderRepository.findAll();
    }

    public void findById(Long id) {
        purchaseOrderRepository.findById(id);
    }

    public void beforeEdit(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrderRepository.findById(purchaseOrder.getId());
    }

    public void edit() {
        purchaseOrderRepository.edit(this.purchaseOrder);
        this.purchaseOrderList = purchaseOrderRepository.findAll();
        messageUtill.showInfo("Order for purchase Edited Successfully", "Order Edited");
    }

    public void delete(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.delete(purchaseOrder);
        purchaseOrderList = purchaseOrderRepository.findAll();
        messageUtill.showInfo("Order for purchase Deleted Successfully", "Order Deleted" );
    }
}