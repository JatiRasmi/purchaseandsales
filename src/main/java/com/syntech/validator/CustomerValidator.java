/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.validator;

import com.syntech.model.Customer;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.CustomerRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.validator.FacesValidator;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesValidator("customerValidator")
public class CustomerValidator extends AbstractValidator<Customer> {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    protected AbstractRepository getRepository() {
        return customerRepository;
    }

}
