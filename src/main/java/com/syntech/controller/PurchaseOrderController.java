/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.PurchaseOrder;
import com.syntech.model.PurchaseOrderDetail;
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
    private Integer index;

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    @Inject
    MessageUtill messageUtill;

    @Inject
    CalculationUtill calculationUtill;

    public PurchaseOrderController() {
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @PostConstruct
    public void init() {
        this.purchaseOrder = new PurchaseOrder();
        this.purchaseOrderList = purchaseOrderRepository.findAll();
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
        this.index = null;
    }

    public void beforeCreatePod() {
        this.purchaseOrderDetail = new PurchaseOrderDetail();
    }

    //purchase order details
    public void addToList() {
        purchaseOrderDetail.setPurchaseOrder(purchaseOrder);
        if (purchaseOrder.getPurchaseOrderDetailList() == null || purchaseOrder.getPurchaseOrderDetailList().isEmpty()) {
            purchaseOrder.setPurchaseOrderDetailList(new ArrayList<>());
        }
        purchaseOrder.getPurchaseOrderDetailList().add(purchaseOrderDetail);
        messageUtill.showInfo("Order Details for purchase Added Successfully", "Order Added");
        calculateTotalAmount();
        purchaseOrderDetail = new PurchaseOrderDetail();

    }

    public void deleteFromList(PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrder.getPurchaseOrderDetailList().remove(purchaseOrderDetail);
        messageUtill.showInfo("Order Details for purchase removed successfully", "Order Removed");
        calculateTotalAmount();
    }

    public void beforeEditList(int index) {
        this.index = index;
        for (int i = 0; i < purchaseOrder.getPurchaseOrderDetailList().size(); i++) {
            if (i == index) {
                this.purchaseOrderDetail = purchaseOrder.getPurchaseOrderDetailList().get(index);
            }
        }
        System.out.println("index " + index);
    }

    public void editFromList() {
        purchaseOrder.getPurchaseOrderDetailList().set(index, purchaseOrderDetail);
        messageUtill.showInfo("Order Detail for purchase Edited Successfully", "Order Edited");
        calculateTotalAmount();
        index = null;
    }

    public void subtotalCalculate() {
        BigDecimal subtotal = calculationUtill.calculateSubtotal(purchaseOrderDetail.getQuantity(), purchaseOrderDetail.getRate());
        this.purchaseOrderDetail.setSubTotal(subtotal);
    }

    public void calculateDiscountAmountAndSubTotalAfterDiscount() {
        BigDecimal discountAmount = calculationUtill.calculateDiscount(purchaseOrderDetail.getSubTotal(), purchaseOrderDetail.getDiscount());
        this.purchaseOrderDetail.setDiscountAmount(discountAmount);

        BigDecimal subafterdiscount = calculationUtill.calculateSubtotalAfterDiscount(purchaseOrderDetail.getSubTotal(), purchaseOrderDetail.getDiscountAmount());
        this.purchaseOrderDetail.setSubTotalAfterDiscount(subafterdiscount);
    }

    public void calculateVatAmountAndTotalAmount() {
        BigDecimal vatAmount = calculationUtill.calculateVat(purchaseOrderDetail.getSubTotalAfterDiscount(), purchaseOrderDetail.getVat());
        this.purchaseOrderDetail.setVatAmount(vatAmount);

        BigDecimal totalAmount = calculationUtill.calculateTotal(purchaseOrderDetail.getSubTotalAfterDiscount(), purchaseOrderDetail.getVatAmount());
        this.purchaseOrderDetail.setTotalAmount(totalAmount);
    }

    public void create() {
        purchaseOrderRepository.create(purchaseOrder);
        this.purchaseOrderList = purchaseOrderRepository.findAll();
        messageUtill.showInfo("Order for purchase Added Successfully", "Order Added");
    }

    public void delete(PurchaseOrder purchaseOrder) {
        purchaseOrder = purchaseOrderRepository.eagerload(purchaseOrder.getId());
        if (purchaseOrder == null) {
            messageUtill.showError("Message", "Purchase Order delete failed !!");
            return;
        }
        purchaseOrderRepository.delete(purchaseOrder);
        purchaseOrderList = purchaseOrderRepository.findAll();
        messageUtill.showInfo("Order for purchase Deleted Successfully", "Order Deleted");
    }

    public void beforeEdit(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrderRepository.eagerload(purchaseOrder.getId());
    }

    public void edit() {
        if (purchaseOrder == null) {
            messageUtill.showError("Message", "Purchase Order edit failed !!");
            return;
        }
        purchaseOrderRepository.edit(this.purchaseOrder);
        this.purchaseOrderList = purchaseOrderRepository.findAll();
        messageUtill.showInfo("Order for purchase Edited Successfully", "Order Edited");
    }

    public String beforeView(PurchaseOrder purchaseOrder) {
        return "/purchase/purchaseView.xhtml?faces-redirect=true&id=" + purchaseOrder.getId();
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void view() {
        purchaseOrder = purchaseOrderRepository.eagerload(id);
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
}
