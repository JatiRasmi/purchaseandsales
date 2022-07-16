/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.SalesOrder;
import com.syntech.model.SalesOrderDetail;
import com.syntech.repository.SalesOrderDetailRepository;
import com.syntech.repository.SalesOrderRepository;
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
public class SalesOrderController implements Serializable {

    private SalesOrder salesOrder;
    private List<SalesOrder> salesOrderList;
    private SalesOrderDetail salesOrderDetail;
    @Inject
    private SalesOrderRepository salesOrderRepository;
    @Inject
    private SalesOrderDetailController salesOrderDetailController;
    @Inject
    private SalesOrderDetailRepository salesOrderDetailRepository;
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
        this.salesOrder = salesOrderRepository.findById(salesOrder.getId());
    }

    public void edit() {
        salesOrderRepository.edit(this.salesOrder);
        this.salesOrderList = salesOrderRepository.findAll();
        messageUtill.showInfo("Order for purchase Edited Successfully", "Order Edited");
    }

    public void delete(SalesOrder salesOrder) {
        salesOrderRepository.delete(salesOrder);
        salesOrderList = salesOrderRepository.findAll();
        messageUtill.showInfo("Order for sales Deleted Successfully", "Order Deleted");
    }
}
