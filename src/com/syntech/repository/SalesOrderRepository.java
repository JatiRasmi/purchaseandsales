/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.SalesOrder;

/**
 *
 * @author rasmi
 */
public class SalesOrderRepository extends AbstractRepository<SalesOrder>{
    @Override
    public void edit(SalesOrder u) {
        super.findAll().stream()
                .filter(n -> n.getId().equals(u.getId()))
                .forEach((SalesOrder un) -> {
                    un.setDate(u.getDate());
                    un.setCustomer(u.getCustomer()); 
                });
    }    
}
