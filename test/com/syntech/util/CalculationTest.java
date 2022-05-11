/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author rasmi
 */
@RunWith(Parameterized.class)
public class CalculationTest {

    private Calculation calculation;
    private Long rate;
    private Long quantity;
    private Long subtotal;
    private Long discount;
    private Long discountamount;
    private Long subtotalafterdiscount;
    private Long vat;
    private Long vatamount;
    private Long totalamount;
    Long actual;

    
    public CalculationTest(Long rate, Long quantity, Long subtotal, Long discount, Long discountamount, Long subtotalafterdiscount, Long vat, Long vatamount, Long totalamount) {
        this.calculation = new Calculation();
        this.quantity = quantity;
        this.rate = rate;
        this.subtotal = subtotal;
        this.discount = discount;
        this.discountamount = discountamount;
        this.subtotalafterdiscount = subtotalafterdiscount;
        this.vat = vat;
        this.vatamount = vatamount;
        this.totalamount = totalamount;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {0L, 0L, 0L, 1L, 0L, 0L, 6L, 0L, 0L},  //obj 1
            {10L, 50L, 500L, 10L, 50L, 450L, 10L, 45L, 495L},}); //obj 2
//  rate,quantity,subtotal(expected),discount, discountamount(expected), subtotalafterdiscount(expected), vat, vatamount(expected),total(expected)
    }

    @Test
    public void calculateSubtotal() {
        System.out.println("Subtotal");
        actual = calculation.calculateSubtotal(rate, quantity);
        Assert.assertEquals(subtotal, actual);
    }

    /**
     * Test of calculateDiscount method, of class Calculation.
     */
    @Test
    public void testCalculateDiscount() {
        System.out.println("calculateDiscount");
        actual = calculation.calculateDiscount(subtotal, discount);
        Assert.assertEquals(discountamount, actual);
    }

    /**
     * Test of calculateSubtotalAfterDiscount method, of class Calculation.
     */
    @Test
    public void testCalculateSubtotalAfterDiscount() {
        System.out.println("calculateSubtotalAfterDiscount");
        actual = calculation.calculateSubtotalAfterDiscount(subtotal, discountamount);
        Assert.assertEquals(subtotalafterdiscount, actual);
    }

    /**
     * Test of calculateVat method, of class Calculation.
     */
    @Test
    public void testCalculateVat() {
        System.out.println("calculateVat");
        actual = calculation.calculateVat(subtotalafterdiscount, vat);
        Assert.assertEquals(vatamount, actual);
    }

    /**
     * Test of calculateTotal method, of class Calculation.
     */
    @Test
    public void testCalculateTotal() {
        System.out.println("calculateTotal");
        actual = calculation.calculateTotal(subtotalafterdiscount,vatamount);
        Assert.assertEquals(totalamount,actual);
    }
    
}
