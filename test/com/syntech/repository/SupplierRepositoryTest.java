/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Supplier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rasmi
 */
public class SupplierRepositoryTest {
    
    public SupplierRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of edit method, of class SupplierRepository.
     */
    @Test
    public void testCreate() {
        SupplierRepository supplierRepository = new SupplierRepository();
        Supplier s = new Supplier(1L,"abc","bkt","abc@gmail.com","9856231458","abc");
        supplierRepository.create(s);
        Assert.assertEquals("abc", supplierRepository.findById(1L).getName());
    }
    
    public void testDelete(){
        SupplierRepository supplierRepository = new  SupplierRepository();
        Supplier s = new Supplier(2L,"xyz","ktm","xyz@gmail.com","9852634178","xyz");
        supplierRepository.create(s);
        Supplier s1 = supplierRepository.findById(1L);
        Assert.assertEquals("xyz",s1 .getName());
        supplierRepository.delete(s1);
        Assert.assertEquals(null, supplierRepository.findById(1L));

    }
    public void testEdit(){
        SupplierRepository supplierRepository = new  SupplierRepository();
        Supplier s = new Supplier(2L,"xyz","ktm","xyz@gmail.com","9852634178","xyz");
        supplierRepository.create(s);
        Supplier s1 = supplierRepository.findById(1L);
        s1.setName("abc");
        supplierRepository.edit(s1);
        Assert.assertEquals("xyz",supplierRepository.findById(2L).getName());
    }
    
}
