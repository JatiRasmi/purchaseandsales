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
public class ValidatorTest {
    
    public ValidatorTest() {
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
     * Test of isValidString method, of class Validator.
     */
    @Test
    public void testIsValidString() {
        System.out.println("isValidString");
//        String str = "";
//        boolean expResult = false;
//        boolean result = Validator.isValidString(str);
//        assertEquals(expResult, result);

          assertEquals(true,Validator.isValidString("rasmi"));
          assertEquals(true,Validator.isValidString("RASMI"));
          assertEquals(true,Validator.isValidString("rAsmI"));
          assertEquals(false, Validator.isValidString(" "));
          assertFalse(Validator.isValidString("a@smi!"));
          assertTrue(Validator.isValidString("Rasmi"));
          
    }

    /**
     * Test of isValidNumber method, of class Validator.
     */
    @Test
    public void testIsValidNumber() {
        System.out.println("isValidNumber");
        assertEquals(false,Validator.isValidNumber("985478"));
        assertEquals(true,Validator.isValidNumber("9841526378"));
        assertTrue(Validator.isValidNumber("9841526378"));
        assertFalse(Validator.isValidNumber("8596747894"));
    }

    /**
     * Test of isValidEmail method, of class Validator.
     */
    @Test
    public void testIsValidEmail() {
        System.out.println("isValidEmail");
        assertEquals(false, Validator.isValidEmail("rasm!@gmail.com"));
        assertEquals(true,Validator.isValidEmail("rasmi@gmail.com"));
        assertTrue(Validator.isValidEmail("rasmi01@gmail.com"));
        assertTrue(Validator.isValidEmail("rasmi.jati@gmail.com"));
        assertFalse(Validator.isValidEmail("rasmi01@gma!l.com"));
        assertFalse(Validator.isValidEmail("rasmi"));
    }
    
}
