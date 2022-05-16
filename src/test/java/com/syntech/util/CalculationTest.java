/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
//import java.util.Arrays;
//import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author rasmi
 */
@RunWith(DataProviderRunner.class)
public class CalculationTest {

//    subtotal---------------------------
    @DataProvider
    public static Object[][] subTotalData() {
        return new Object[][]{
            {0L, 0L, 0L}, //obj 1
            {10L, 50L, 500L}, //obj 2
            {2L, 2L, 4L}
        //  rate,quantity,subtotal(expected)
        };
    }

    @Test
    @UseDataProvider("subTotalData")
    public void subTotalDataProviderTest(Long rate, Long quantity, Long subtotal) {
        Long actual = Calculation.calculateSubtotal(rate, quantity);
        Assert.assertEquals(subtotal, actual);
    }

//    discount------------------------
    @DataProvider
    public static Object[][] discountData() {
        return new Object[][]{
            {4L, 0L, 0L}, //obj 1
            {500L, 5L, 25L}, //obj 2
            {40L, 5L, 2L}
        //  subtotal(expected), discount, discountamount(expected)
        };
    }

    @Test
    @UseDataProvider("discountData")
    public void discountDataProviderTest(Long subtotal, Long discount, Long discountamount) {
        Long actual = Calculation.calculateDiscount(subtotal, discount);
        Assert.assertEquals(discountamount, actual);
    }

//    sub total sfter discount ------------------------
    @DataProvider
    public static Object[][] subTotalAfterDiscountData() {
        return new Object[][]{
            {4L, 0L, 4L}, //obj 1
            {500L, 25L, 475L}, //obj 2
            {40L, 2L, 38L}
        //  subtotal(expected), discountamount, subtotalafterdiscount(expected)
        };
    }

    @Test
    @UseDataProvider("subTotalAfterDiscountData")
    public void subtotalAfterDiscountDataProviderTest(Long subtotal, Long discountamount, Long subtotalafterdiscountamount) {
        Long actual = Calculation.calculateSubtotalAfterDiscount(subtotal, discountamount);
        Assert.assertEquals(subtotalafterdiscountamount, actual);
    }

//    vat amount---------------
    @DataProvider
    public static Object[][] vatData() {
        return new Object[][]{
            {4L, 0L, 0L}, //obj 1
            {500L, 5L, 25L}, //obj 2
            {40L, 5L, 2L}
//             subtotalafterdiscount,   vat,  vatamount(expected)
        };
    }

    @Test
    @UseDataProvider("vatData")
    public void vatDataProviderTest(Long subtotalafterdiscountamount, Long vat, Long vatamount) {
        Long actual = Calculation.calculateVat(subtotalafterdiscountamount, vat);
        Assert.assertEquals(vatamount, actual);
    }
    
    
//    total-----------
    @DataProvider
    public static Object[][] totalAmountData() {
        return new Object[][]{
            {4L, 0L, 4L}, //obj 1
            {500L, 5L, 505L}, //obj 2
            {40L, 5L, 45L}
//             subtotalafterdiscount,  vatamount, total(expected)
        };
    }

    @Test
    @UseDataProvider("totalAmountData")
    public void totalAmountDataProviderTest(Long subtotalafterdiscountamount, Long vatamount, Long total) {
        Long actual = Calculation.calculateTotal(subtotalafterdiscountamount, vatamount);
        Assert.assertEquals(total, actual);
    }
}

