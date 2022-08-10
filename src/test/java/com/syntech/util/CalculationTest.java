/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

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
            {BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO}, //obj 1
            {new BigDecimal("10"), new BigDecimal("50"), new BigDecimal("500")}, //obj 2
            {new BigDecimal("2"), new BigDecimal("2"), new BigDecimal("4")}
        //  rate,quantity,subtotal(expected)
        };
    }

    @Test
    @UseDataProvider("subTotalData")
    public void subTotalDataProviderTest(BigDecimal rate, BigDecimal quantity, BigDecimal subtotal) {
        BigDecimal actual = Calculation.calculateSubtotal(rate, quantity);
        Assert.assertEquals(subtotal, actual);
    }

//    discount------------------------
    @DataProvider
    public static Object[][] discountData() {
        return new Object[][]{
            {new BigDecimal("4"), new BigDecimal("0"), new BigDecimal("0")}, //obj 1
            {new BigDecimal("500"), new BigDecimal("5"), new BigDecimal("25")}, //obj 2
            {new BigDecimal("40"), new BigDecimal("5"), new BigDecimal("2")}
        //  subtotal(expected), discount, discountamount(expected)
        };
    }

    @Test
    @UseDataProvider("discountData")
    public void discountDataProviderTest(BigDecimal subtotal, BigDecimal discount, BigDecimal discountamount) {
        BigDecimal actual = Calculation.calculateDiscount(subtotal, discount);
        Assert.assertEquals(discountamount, actual);
    }

//    sub total sfter discount ------------------------
    @DataProvider
    public static Object[][] subTotalAfterDiscountData() {
        return new Object[][]{
            {new BigDecimal("4"), new BigDecimal("0"), new BigDecimal("4")}, //obj 1
            {new BigDecimal("500"), new BigDecimal("25"), new BigDecimal("475")}, //obj 2
            {new BigDecimal("40"), new BigDecimal("2"), new BigDecimal("38")}
        //  subtotal(expected), discountamount, subtotalafterdiscount(expected)
        };
    }

    @Test
    @UseDataProvider("subTotalAfterDiscountData")
    public void subtotalAfterDiscountDataProviderTest(BigDecimal subtotal, BigDecimal discountamount, BigDecimal subtotalafterdiscountamount) {
        BigDecimal actual = Calculation.calculateSubtotalAfterDiscount(subtotal, discountamount);
        Assert.assertEquals(subtotalafterdiscountamount, actual);
    }

//    vat amount---------------
    @DataProvider
    public static Object[][] vatData() {
        return new Object[][]{
            {new BigDecimal("4"), new BigDecimal("0"), new BigDecimal("0")}, //obj 1
            {new BigDecimal("500"), new BigDecimal("5"), new BigDecimal("25")}, //obj 2
            {new BigDecimal("40"), new BigDecimal("5"), new BigDecimal("2")}
//             subtotalafterdiscount,   vat,  vatamount(expected)
        };
    }

    @Test
    @UseDataProvider("vatData")
    public void vatDataProviderTest(BigDecimal subtotalafterdiscountamount, BigDecimal vat, BigDecimal vatamount) {
        BigDecimal actual = Calculation.calculateVat(subtotalafterdiscountamount, vat);
        Assert.assertEquals(vatamount, actual);
    }
    
    
//    total-----------
    @DataProvider
    public static Object[][] totalAmountData() {
        return new Object[][]{
            {new BigDecimal("4"), new BigDecimal("0"), new BigDecimal("4")}, //obj 1
            {new BigDecimal("500"), new BigDecimal("5"), new BigDecimal("505")}, //obj 2
            {new BigDecimal("40"), new BigDecimal("5"), new BigDecimal("45")}
//             subtotalafterdiscount,  vatamount, total(expected)
        };
    }

    @Test
    @UseDataProvider("totalAmountData")
    public void totalAmountDataProviderTest(BigDecimal subtotalafterdiscountamount, BigDecimal vatamount, BigDecimal total) {
        BigDecimal actual = Calculation.calculateTotal(subtotalafterdiscountamount, vatamount);
        Assert.assertEquals(total, actual);
    }
}

