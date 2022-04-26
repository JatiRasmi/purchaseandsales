/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmi
 */
public class customerRepository {
    private List<Customer> customerList;

    public customerRepository(){
        customerList = new ArrayList<>();
    }

    public void create(Customer customerList) {
        this.customerList.add(customerList);
    }
    
    public List<Customer> findAll() {
        return customerList;
    }

    public Customer findById(Long id) {
        for (Customer c : customerList) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
    
    public void delete(Customer customer) {
        this.customerList.remove(customer);
    }
        
    public void edit(Customer cust) {
        customerList.stream().filter(x -> x.getId().equals(cust.getId())).forEach(cu -> {
            cu.setName(cust.getName());
            cu.setAddress(cust.getAddress());
            cu.setEmail(cust.getEmail());
            cu.setContact(cust.getContact());
        });
    }
}
