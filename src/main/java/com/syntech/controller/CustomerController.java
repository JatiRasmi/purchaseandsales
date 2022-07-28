/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Customer;
import com.syntech.repository.CustomerRepository;
import com.syntech.util.MessageUtill;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CustomerController implements Serializable {

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    private Customer customer;
    private List<Customer> customerList;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private MessageUtill messageUtill;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @PostConstruct
    public void init() {
        this.customer = new Customer();
        this.customerList = customerRepository.findAll();
    }

    public void beforeCreate() {
        this.customer = new Customer();
    }

    public void create() {
        try {
            customerRepository.create(customer);
            this.customerList = customerRepository.findAll();
            messageUtill.showInfo("Customer Added Successfully", "Added Customer");
            logger.log(Level.INFO, " Customer Created Successfully!!!:");
        } catch (Exception e) {
            messageUtill.setValidationFlag();
            logger.log(Level.WARNING, " Customer is null!!:", e);
        }
    }

    public void beforeEdit(Customer customer) {
        this.customer = customerRepository.findById(customer.getId());
    }

    public void edit() {
        try {
            customerRepository.edit(this.customer);
            this.customerList = customerRepository.findAll();
            messageUtill.showInfo("Customer Edited Successfully", "Edited Customer");
            logger.log(Level.INFO, " Customer Edited Successfully!!!:");

        } catch (NullPointerException e) {
            logger.log(Level.WARNING, " Customer is null!!:", e);
        }
    }

    public void delete(Customer customer) {
        try {
            customerRepository.delete(customer);
            customerList = customerRepository.findAll();
            messageUtill.showInfo("Customer Deleted Successfully", "Deleted Customer");
            logger.log(Level.INFO, " Customer Deleted Successfully!!!:");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, " Customer is Null!!!:", e);
        }
    }
}
