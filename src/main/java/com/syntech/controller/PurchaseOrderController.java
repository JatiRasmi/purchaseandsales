/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.PurchaseOrder;
import com.syntech.model.PurchaseOrderDetail;
import com.syntech.repository.PurchaseOrderDetailRepository;
import com.syntech.repository.PurchaseOrderRepository;
import com.syntech.util.CalculationUtill;
import com.syntech.util.MessageUtill;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private PurchaseOrderDetail purchaseOrderDetail;

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    @Inject
    private PurchaseOrderDetailController purchaseOrderDetailController;

    @Inject
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Inject
    MessageUtill messageUtill;

    @Inject
    CalculationUtill calculationUtill;

    public PurchaseOrderController() {
    }

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

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    @PostConstruct
    public void init() {
        this.purchaseOrder = new PurchaseOrder();
    }

    public PurchaseOrderDetail getPurchaseOrderDetail() {
        return purchaseOrderDetail;
    }

    public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetail = purchaseOrderDetail;
    }

    public void beforeCreate() {
        this.purchaseOrder = new PurchaseOrder();
        this.purchaseOrderDetail = new PurchaseOrderDetail();
    }

    public void addToList() {
        purchaseOrderDetail.setPurchaseOrder(purchaseOrder);
        if (purchaseOrder.getPurchaseOrderDetailList() == null || purchaseOrder.getPurchaseOrderDetailList().isEmpty()) {
            purchaseOrder.setPurchaseOrderDetailList(new ArrayList<>());
        }
        purchaseOrder.getPurchaseOrderDetailList().add(purchaseOrderDetail);
        messageUtill.showInfo("Order for purchase Added Successfully", "Order Added");
        purchaseOrderDetail = new PurchaseOrderDetail();
        
        
    }

    public void deleteFromList(PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrder.getPurchaseOrderDetailList().remove(purchaseOrderDetail);
//        this.purchaseOrderDetail = null;
        messageUtill.showInfo("Order for purchase removed successfully", "Order Removed");
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
        messageUtill.showInfo("Order for purchase Deleted Successfully", "Order Deleted");
    }

    public void subtotalCalculate() {
        BigDecimal subtotal = calculationUtill.calculateSubtotal(purchaseOrderDetail.getQuantity(), purchaseOrderDetail.getRate());
        this.purchaseOrderDetail.setSubtotal(subtotal);
    }

    public void calculateDiscountAmountAndSubTotalAfterDiscount() {
        BigDecimal discountAmount = calculationUtill.calculateDiscount(purchaseOrderDetail.getSubtotal(), purchaseOrderDetail.getDiscount());
        this.purchaseOrderDetail.setDiscountamount(discountAmount);

        BigDecimal subafterdiscount = calculationUtill.calculateSubtotalAfterDiscount(purchaseOrderDetail.getSubtotal(), purchaseOrderDetail.getDiscountamount());
        this.purchaseOrderDetail.setSubtotalafterdiscount((subafterdiscount));
    }

    public void calculateVatAmountAndTotalAmount() {
        BigDecimal vatAmount = calculationUtill.calculateVat(purchaseOrderDetail.getSubtotalafterdiscount(), purchaseOrderDetail.getVat());
        this.purchaseOrderDetail.setVatamount(vatAmount);

        BigDecimal totalAmount = calculationUtill.calculateTotal(purchaseOrderDetail.getSubtotalafterdiscount(), purchaseOrderDetail.getVatamount());
        this.purchaseOrderDetail.setTotalamount(totalAmount);
    }

}
