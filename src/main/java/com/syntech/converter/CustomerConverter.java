/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.converter;

import com.syntech.model.Customer;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.CustomerRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesConverter(value = "customerConverter", forClass = Customer.class)
public class CustomerConverter extends AbstractConverter<Customer> {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    protected AbstractRepository getRepository() {
        return customerRepository;
    }
}
