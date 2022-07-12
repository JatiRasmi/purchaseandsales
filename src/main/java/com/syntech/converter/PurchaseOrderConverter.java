/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.converter;

import com.syntech.model.Product;
import com.syntech.model.PurchaseOrder;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.PurchaseOrderRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */

@RequestScoped
@FacesConverter(value = "purchaseOrderConverter", forClass = PurchaseOrder.class)

public class PurchaseOrderConverter extends AbstractConverter<PurchaseOrder> {
    
    @Inject
    private PurchaseOrderRepository productOrderRepository;
    
    @Override
    protected AbstractRepository getRepository() {
        return productOrderRepository;
    }   
}