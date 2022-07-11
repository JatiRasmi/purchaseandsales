/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.validator;

import com.syntech.model.Supplier;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.SupplierRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.validator.FacesValidator;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesValidator("supplierValidator")
public class SupplierValidator extends AbstractValidator<Supplier> {

    @Inject
    private SupplierRepository supplierRepository;

    @Override
    protected AbstractRepository getRepository() {
        return supplierRepository;
    }
}
