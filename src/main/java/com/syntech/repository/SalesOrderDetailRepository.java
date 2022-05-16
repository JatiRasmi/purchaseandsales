/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.SalesOrderDetail;

/**
 *
 * @author rasmi
 */
public class SalesOrderDetailRepository extends AbstractRepository<SalesOrderDetail> {
    @Override
    public void edit(SalesOrderDetail u) {
        super.findAll().stream()
                .filter(n -> n.getId().equals(u.getId()))
                .forEach((SalesOrderDetail un) -> {
                    un.setSalesorder(u.getSalesorder());
                    un.setProduct(u.getProduct());
                    un.setQuantity(u.getQuantity());
                    un.setRate(u.getRate());
                    un.setSubtotal(u.getSubtotal());
                    un.setDiscount(u.getDiscount());
                    un.setVat(u.getVat());
                    un.setTotalamount(u.getTotalamount());
                });
    }
}
