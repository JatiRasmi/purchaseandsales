/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rasmi
 */
public class CalculationTest {
    
    public CalculationTest() {
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
     * Test of calculateSubtotal method, of class Calculation.
     */
    @Test
    public void calculateSubtotal() {
        System.out.println("calculateSubtotal");
        Long rate = 100L;
        Long quantity = 5L;
	Long expected = 500L;
	Long actual = (rate* quantity);  
	assertEquals(expected,actual);
	 }
    /**
     * Test of calculateDiscount method, of class Calculation.
     */
    @Test
    public void testCalculateDiscount() {
        System.out.println("calculateDiscount");
        Long subtotal = 500L;
        Long discount = 6L;
        Long expected = 30L;
        Long actual = (subtotal*discount)/100;
        assertEquals(expected, actual);
    }

    /**
     * Test of calculateSubtotalAfterDiscount method, of class Calculation.
     */
    @Test
    public void testCalculateSubtotalAfterDiscount() {
        System.out.println("calculateSubtotalAfterDiscount");
        Long subtotal = 500L;
        Long discountAmount = 30L;
        Long expected = 470L ;
        Long actual = (subtotal-discountAmount);
        assertEquals(expected,actual);
    }

    /**
     * Test of calculateVat method, of class Calculation.
     */
    @Test
    public void testCalculateVat() {
        System.out.println("calculateVat");
        Long subtotalafterdiscount = 480L;
        Long vat = 5L;
        Long expected = 24L;
        Long actual = (subtotalafterdiscount*vat)/100;
        assertEquals(expected, actual);
    }

    /**
     * Test of calculateTotal method, of class Calculation.
     */
    @Test
    public void testCalculateTotal() {
        System.out.println("calculateTotal");
        Long subtotalafterdiscount = 480L;
        Long vat = 24L;
        Long expected = 504L;
        Long actual = (subtotalafterdiscount+vat);
        assertEquals(expected,actual);
    }
    
}
