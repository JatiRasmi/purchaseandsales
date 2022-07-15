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
        calculateTotalAmount();
        purchaseOrderDetail = new PurchaseOrderDetail();

    }

    public void deleteFromList(PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrder.getPurchaseOrderDetailList().remove(purchaseOrderDetail);
        messageUtill.showInfo("Order for purchase removed successfully", "Order Removed");
        calculateTotalAmount();
    }

    public void subtotalCalculate() {
        BigDecimal subtotal = calculationUtill.calculateSubtotal(purchaseOrderDetail.getQuantity(), purchaseOrderDetail.getRate());
        this.purchaseOrderDetail.setSubTotal(subtotal);
    }

    public void calculateDiscountAmountAndSubTotalAfterDiscount() {
        BigDecimal discountAmount = calculationUtill.calculateDiscount(purchaseOrderDetail.getSubTotal(), purchaseOrderDetail.getDiscount());
        this.purchaseOrderDetail.setDiscountAmount(discountAmount);

        BigDecimal subafterdiscount = calculationUtill.calculateSubtotalAfterDiscount(purchaseOrderDetail.getSubTotal(), purchaseOrderDetail.getDiscountAmount());
        this.purchaseOrderDetail.setsubTotalAfterDiscount(subafterdiscount);
    }

    public void calculateVatAmountAndTotalAmount() {
        BigDecimal vatAmount = calculationUtill.calculateVat(purchaseOrderDetail.getsubTotalAfterDiscount(), purchaseOrderDetail.getVat());
        this.purchaseOrderDetail.setVatAmount(vatAmount);

        BigDecimal totalAmount = calculationUtill.calculateTotal(purchaseOrderDetail.getsubTotalAfterDiscount(), purchaseOrderDetail.getVatAmount());
        this.purchaseOrderDetail.setTotalAmount(totalAmount);
    }

    public void calculateTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal vatAmount = BigDecimal.ZERO;

        if (purchaseOrder == null) {
            return;
        }
        if (purchaseOrder.getPurchaseOrderDetailList() != null) {
            for (PurchaseOrderDetail purchaseOrderDetailList : purchaseOrder.getPurchaseOrderDetailList()) {
                subTotal = subTotal.add(purchaseOrderDetailList.getSubTotal());
                discountAmount = discountAmount.add(purchaseOrderDetailList.getDiscountAmount());
                vatAmount = vatAmount.add(purchaseOrderDetailList.getVatAmount());
                totalAmount = totalAmount.add(purchaseOrderDetailList.getTotalAmount());
            }
            this.purchaseOrder.setSubTotal(subTotal);
            this.purchaseOrder.setDiscountAmount(discountAmount);
            this.purchaseOrder.setVatAmount(vatAmount);
            this.purchaseOrder.setTotalAmount(totalAmount);
        }
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

}
