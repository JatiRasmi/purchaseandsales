/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.converter;

import com.syntech.model.Supplier;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.SupplierRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesConverter(value = "supplierConverter", forClass = Supplier.class)
public class SupplierConverter extends AbstractConverter<Supplier> {

    @Inject
    private SupplierRepository supplierRepository;

    @Override
    protected AbstractRepository getRepository() {
        return supplierRepository;
    }
}
