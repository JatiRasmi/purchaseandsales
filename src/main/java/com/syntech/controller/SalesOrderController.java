/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.SalesOrder;
import com.syntech.model.SalesOrderDetail;
import com.syntech.repository.SalesOrderRepository;
import com.syntech.util.CalculationUtill;
import com.syntech.util.MessageUtill;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class SalesOrderController implements Serializable {

    private SalesOrder salesOrder;
    private List<SalesOrder> salesOrderList;
    private SalesOrderDetail salesOrderDetail;
    private Integer index;

    @Inject
    private SalesOrderRepository salesOrderRepository;

    @Inject
    MessageUtill messageUtill;

    @Inject
    CalculationUtill calculationUtill;

    public SalesOrderController() {
    }

    public List<SalesOrder> getSalesOrderList() {
        return salesOrderList;
    }

    public void setSalesOrderList(List<SalesOrder> salesOrderList) {
        this.salesOrderList = salesOrderList;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    @PostConstruct
    public void init() {
        this.salesOrder = new SalesOrder();
        this.salesOrderList = salesOrderRepository.findAll();
    }

    public SalesOrderDetail getSalesOrderDetail() {
        return salesOrderDetail;
    }

    public void setSalesOrderDetail(SalesOrderDetail salesOrderDetail) {
        this.salesOrderDetail = salesOrderDetail;
    }

    public void beforeCreate() {
        this.salesOrder = new SalesOrder();
        this.salesOrderDetail = new SalesOrderDetail();
        this.index = null;
    }

    public void beforeCreateSod() {
        this.salesOrderDetail = new SalesOrderDetail();
    }

    public void addToList() {
        salesOrderDetail.setSalesOrder(salesOrder);
        if (salesOrder.getSalesOrderDetailList() == null || salesOrder.getSalesOrderDetailList().isEmpty()) {
            salesOrder.setSalesOrderDetailList(new ArrayList<>());
        }
        salesOrder.getSalesOrderDetailList().add(salesOrderDetail);
        messageUtill.showInfo("Order for Sales Added Successfully", "Order Added");
        calculateTotalAmount();
        salesOrderDetail = new SalesOrderDetail();
    }

    public void deleteFromList(SalesOrderDetail salesOrderDetail) {
        salesOrder.getSalesOrderDetailList().remove(salesOrderDetail);
        messageUtill.showInfo("Order for sales removed successfully", "Order Removed");
        calculateTotalAmount();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void beforeEditList(int index) {
        this.index = index;
        for (int i = 0; i < salesOrder.getSalesOrderDetailList().size(); i++) {
            if (i == index) {
                this.salesOrderDetail = salesOrder.getSalesOrderDetailList().get(index);
            }
        }
        System.out.println("index " + index);
    }

    public void editFromList() {
        salesOrder.getSalesOrderDetailList().set(index, salesOrderDetail);
        messageUtill.showInfo("Order Detail for Sales Edited Successfully", "Order Edited");
        calculateTotalAmount();
        index = null;
    }

    public void subtotalCalculate() {
        BigDecimal subtotal = calculationUtill.calculateSubtotal(salesOrderDetail.getQuantity(), salesOrderDetail.getRate());
        this.salesOrderDetail.setSubTotal(subtotal);
    }

    public void calculateDiscountAmountAndSubTotalAfterDiscount() {
        BigDecimal discountAmount = calculationUtill.calculateDiscount(salesOrderDetail.getSubTotal(), salesOrderDetail.getDiscount());
        this.salesOrderDetail.setDiscountAmount(discountAmount);

        BigDecimal subafterdiscount = calculationUtill.calculateSubtotalAfterDiscount(salesOrderDetail.getSubTotal(), salesOrderDetail.getDiscountAmount());
        this.salesOrderDetail.setSubTotalAfterDiscount(subafterdiscount);
    }

    public void calculateVatAmountAndTotalAmount() {
        BigDecimal vatAmount = calculationUtill.calculateVat(salesOrderDetail.getSubTotalAfterDiscount(), salesOrderDetail.getVat());
        this.salesOrderDetail.setVatAmount(vatAmount);

        BigDecimal totalAmount = calculationUtill.calculateTotal(salesOrderDetail.getSubTotalAfterDiscount(), salesOrderDetail.getVatAmount());
        this.salesOrderDetail.setTotalAmount(totalAmount);
    }

    public void calculateTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal vatAmount = BigDecimal.ZERO;

        if (salesOrder == null) {
            return;
        }
        if (salesOrder.getSalesOrderDetailList() != null) {
            for (SalesOrderDetail salesOrderDetailList : salesOrder.getSalesOrderDetailList()) {
                subTotal = subTotal.add(salesOrderDetailList.getSubTotal());
                discountAmount = discountAmount.add(salesOrderDetailList.getDiscountAmount());
                vatAmount = vatAmount.add(salesOrderDetailList.getVatAmount());
                totalAmount = totalAmount.add(salesOrderDetailList.getTotalAmount());
            }
            this.salesOrder.setSubTotal(subTotal);
            this.salesOrder.setDiscountAmount(discountAmount);
            this.salesOrder.setVatAmount(vatAmount);
            this.salesOrder.setTotalAmount(totalAmount);
        }
    }

    public void create() {
        salesOrderRepository.create(salesOrder);
        this.salesOrderList = salesOrderRepository.findAll();
        messageUtill.showInfo("Order for Sales Added Successfully", "Order Added");
    }

    public void findAll() {
        salesOrderRepository.findAll();
    }

    public void findById(Long id) {
        salesOrderRepository.findById(id);
    }

    public void beforeEdit(SalesOrder salesOrder) {
        this.salesOrder = salesOrderRepository.eagerload(salesOrder.getId());

    }

    public void edit() {
        if (salesOrder == null) {
            messageUtill.showError("Message", "Sales Order edit failed !!");
            return;
        }
        salesOrderRepository.edit(this.salesOrder);
        this.salesOrderList = salesOrderRepository.findAll();
        messageUtill.showInfo("Order for Sales Edited Successfully", "Order Edited");
    }

    public void delete(SalesOrder salesOrder) {
        salesOrder = salesOrderRepository.eagerload(salesOrder.getId());
        if (salesOrder == null) {
            messageUtill.showError("Message", "Sales Order delete failed !!");
            return;
        }
        salesOrderRepository.delete(salesOrder);
        salesOrderList = salesOrderRepository.findAll();
        messageUtill.showInfo("Order for sales Deleted Successfully", "Order Deleted");
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void view(SalesOrder salesOrder) {
        this.salesOrder = salesOrderRepository.eagerload(salesOrder.getId());
    }
}
