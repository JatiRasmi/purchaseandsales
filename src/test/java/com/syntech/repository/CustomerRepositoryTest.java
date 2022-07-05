///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.repository;
//
//import com.syntech.model.Customer;
//import org.junit.Assert;
//import org.junit.Test;
//
///**
// *
// * @author rasmi
// */
//public class CustomerRepositoryTest {
//
//    public CustomerRepositoryTest() {
//    }
//
//        /**
//     * Test of edit method, of class CustomerRepository.
//     */
//    
//    
//    @Test
//    public void testCreate() {
//        CustomerRepository customerRepository = new CustomerRepository();
//        Customer c = new Customer(null, "Rasmi", "Bkt", "rasmiiii@gmail.cds", "674768728");
//        customerRepository.create(c);
//        Assert.assertEquals(4, customerRepository.findAll().size());   // 4 --> number of size of row and column in table
//        Assert.assertEquals("Rasmi", customerRepository.findById(74L).getName());
//    }
//    
//    
//    /**
//     * Test of delete method, of class CustomerController.
//     */
////    @Test
////    public void testDelete() {
////        CustomerRepository customerRepository = new CustomerRepository();
////        Customer c = new Customer(9L, "Rasmi", "Bkt", "rasmi@gmail.cds", "67428");
////        customerRepository.create(c);
////        Customer c1 = customerRepository.findById(74L);
////        Assert.assertEquals("Rasmi",c1 .getName());
////        customerRepository.delete(c1);
////        Assert.assertEquals(null, customerRepository.findById(62L));
////        
////    }
////
////    
////
////
////    @Test
////    public void testEdit() {
////        CustomerRepository customerRepository = new CustomerRepository();
////        Customer c = new Customer(6L, "Rasmi", "Bkt", "rasmi@gmail.cds", "67428");
////        customerRepository.create(c);
////
//////        c = null;
////
////        Assert.assertEquals("sanjiv", customerRepository.findById(6L).getName());
////
////        Customer c1 = customerRepository.findById(6L);
////        c1.setName("sanjiv");
////        c1.setAddress("ktm");
////        c1.setEmail("abcde@gmail.com");
////        c1.setContact("9852634178");
////        customerRepository.edit(c1);
//////        c1 = null;
////        Assert.assertEquals("sanjiv", customerRepository.findById(6L).getName());
////        Assert.assertEquals("ktm", customerRepository.findById(6L).getAddress());
////        Assert.assertEquals("abcde@gmail.com",customerRepository.findById(6L).getEmail());
////    }
//}
