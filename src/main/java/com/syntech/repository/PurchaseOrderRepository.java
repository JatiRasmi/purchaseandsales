/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.PurchaseOrder;

/**
 *
 * @author rasmi
 */
public class PurchaseOrderRepository extends AbstractRepository<PurchaseOrder> {
    @Override
    public void edit(PurchaseOrder u) {
        super.findAll().stream()
                .filter(n -> n.getId().equals(u.getId()))
                .forEach((PurchaseOrder un) -> {
                    un.setDate(u.getDate());
                    un.setExpecteddeliverydate(u.getExpecteddeliverydate());
                    un.setSupplierid(u.getSupplierid());
                });
    }
}
