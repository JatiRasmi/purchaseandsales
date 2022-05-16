/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rasmi
 */
public class CustomerRepositoryTest {

    public CustomerRepositoryTest() {
    }

        /**
     * Test of edit method, of class CustomerRepository.
     */
    @Test
    public void testCreate() {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer c = new Customer(1L, "Rasmi", "Bkt", "rasmi@gmail.cds", "67428");
        customerRepository.create(c);
        Assert.assertEquals(1, customerRepository.findAll().size());   // 1 --> number of size
        Assert.assertEquals("Rasmi", customerRepository.findById(1L).getName());
    }
    
    
    /**
     * Test of delete method, of class CustomerController.
     */
    @Test
    public void testDelete() {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer c = new Customer(1L, "Rasmi", "Bkt", "rasmi@gmail.cds", "67428");
        customerRepository.create(c);
        Customer c1 = customerRepository.findById(1L);
        Assert.assertEquals("Rasmi",c1 .getName());
        customerRepository.delete(c1);
        Assert.assertEquals(null, customerRepository.findById(1L));
        
    }

    


    @Test
    public void testEdit() {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer c = new Customer(1L, "Rasmi", "Bkt", "rasmi@gmail.cds", "67428");
        customerRepository.create(c);

//        c = null;

        Assert.assertEquals("Rasmi", customerRepository.findById(1L).getName());

        Customer c1 = customerRepository.findById(1L);
        c1.setName("sanjiv");
        c1.setAddress("ktm");
        c1.setEmail("abcde@gmail.com");
        c1.setContact("9852634178");
        customerRepository.edit(c1);
//        c1 = null;
        Assert.assertEquals("sanjiv", customerRepository.findById(1L).getName());
        Assert.assertEquals("ktm", customerRepository.findById(1L).getAddress());
        Assert.assertEquals("abcde@gmail.com",customerRepository.findById(1L).getEmail());
    }
}
