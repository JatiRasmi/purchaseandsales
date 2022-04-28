/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;

/**
 *
 * @author rasmi
 */
public class CustomerRepository extends AbstractRepository<Customer> {

    @Override
    public void edit(Customer cust) {
        super.findAll().stream()
                .filter(n -> n.getId().equals(cust.getId()))    // compare the list of data from findAll method with the entered id obtained from the gedId method 
                .forEach((Customer cu) -> {
                    cu.setName(cust.getName());
                    cu.setAddress(cust.getAddress());
                    cu.setEmail(cust.getEmail());
                    cu.setContact(cust.getContact());
                });
    }
}
