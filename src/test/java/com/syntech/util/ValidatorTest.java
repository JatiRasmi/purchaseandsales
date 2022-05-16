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
import org.junit.jupiter.api.Assertions;

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
//        Assertions.assertEquals(expResult, result);

        Assertions.assertEquals(true, Validator.isValidString("rasmi"));
        Assertions.assertEquals(true, Validator.isValidString("RASMI"));
        Assertions.assertEquals(true, Validator.isValidString("rAsmI"));
        Assertions.assertEquals(false, Validator.isValidString(" "));
        Assertions.assertFalse(Validator.isValidString("a@smi!"));
        Assertions.assertTrue(Validator.isValidString("Rasmi"));
        Assertions.assertNotNull(Validator.isValidString("sds"));
    }

    /**
     * Test of isValidNumber method, of class Validator.
     */
    @Test
    public void testIsValidNumber() {
        System.out.println("isValidNumber");
        Assertions.assertEquals(false, Validator.isValidNumber("985478"));
        Assertions.assertEquals(true, Validator.isValidNumber("9841526378"));
        Assertions.assertTrue(Validator.isValidNumber("9841526378"));
        Assertions.assertFalse(Validator.isValidNumber("8596747894"));
    }

    /**
     * Test of isValidEmail method, of class Validator.
     */
    @Test
    public void testIsValidEmail() {
        System.out.println("isValidEmail");
        Assertions.assertEquals(false, Validator.isValidEmail("rasm!@gmail.com"));
        Assertions.assertEquals(true, Validator.isValidEmail("rasmi@gmail.com"));
        Assertions.assertTrue(Validator.isValidEmail("rasmi01@gmail.com"));
        Assertions.assertTrue(Validator.isValidEmail("rasmi.jati@gmail.com"));
        Assertions.assertFalse(Validator.isValidEmail("rasmi01@gma!l.com"));
        Assertions.assertFalse(Validator.isValidEmail("rasmi"));
    }

}
