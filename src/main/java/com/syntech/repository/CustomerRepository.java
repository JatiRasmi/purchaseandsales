/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rasmi
 */
@Stateless
public class CustomerRepository extends AbstractRepository<Customer> {

    @PersistenceContext(name = "psDS")   
    private EntityManager em;

    public CustomerRepository() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }  
}
