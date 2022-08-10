/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class DateUtilTest {
    
    public DateUtilTest() {
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
     * Test of removeTime method, of class DateUtil.
     * @throws java.text.ParseException
     */
    @Test
    public void testRemoveTime() throws ParseException {
        System.out.println("removeTime");
        
        String strDate = "2022-08-10:5:3:0";
        Date date = new SimpleDateFormat("yyyy-mm-dd:hh:mm:ss").parse(strDate);
        DateUtil instance = new DateUtil();
        
        String strDates = "2022-08-10:0:0:0";
        Date expResult = new SimpleDateFormat("yyyy-mm-dd:hh:mm:ss").parse(strDates);
        
        Date result = instance.removeTime(date);
        assertEquals(expResult, result);
        assertFalse(false);
        
                
                
                
                
                
                
      
    }
    
}
