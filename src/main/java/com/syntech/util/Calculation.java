/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import java.math.BigDecimal;

/**
 *
 * @author rasmi
 */
public class Calculation {

    public static BigDecimal calculateSubtotal(BigDecimal rate, BigDecimal quantity) {
        BigDecimal subtotal;
        subtotal = quantity.multiply(rate);
        return subtotal;
    }

    public static BigDecimal calculateDiscount(BigDecimal subtotal, BigDecimal discount) {
        BigDecimal discountAmount;
        discountAmount = (subtotal.multiply(discount)).divide(new BigDecimal("100"));
        return discountAmount;
    }

    public static BigDecimal calculateSubtotalAfterDiscount(BigDecimal subtotal, BigDecimal discountAmount) {
        BigDecimal subtotalafterdiscount;
        subtotalafterdiscount = subtotal.subtract(discountAmount);
        return subtotalafterdiscount;
    }

    public static BigDecimal calculateVat(BigDecimal subtotalafterdiscount, BigDecimal vat) {
        BigDecimal vatAmount;
        vatAmount = (subtotalafterdiscount.multiply(vat)).divide(new BigDecimal("100"));
        return vatAmount;
    }

    public static BigDecimal calculateTotal(BigDecimal subtotalafterdiscount, BigDecimal vatAmount) {
        BigDecimal totalAmount;
        totalAmount = subtotalafterdiscount.add(vatAmount);
        return totalAmount;
    }

}
