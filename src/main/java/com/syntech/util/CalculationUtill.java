/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import java.math.BigDecimal;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author rasmi
 */
@RequestScoped
public class CalculationUtill {

    BigDecimal value = new BigDecimal("100");

    public BigDecimal calculateSubtotal(BigDecimal rate, BigDecimal quantity) {
        BigDecimal subtotal;
        if (rate == null || quantity == null) {
            return subtotal = BigDecimal.ZERO;
        } else {
            subtotal = quantity.multiply(rate);
            return subtotal;
        }
    }

    public BigDecimal calculateDiscount(BigDecimal subtotal, BigDecimal discount) {
        BigDecimal discountAmount;
        if (discount == null) {
            return discount = BigDecimal.ZERO; 
        } else if (discount == BigDecimal.ZERO) {
           return discountAmount = BigDecimal.ZERO;    
        } else {
            discountAmount = (subtotal.multiply(discount)).divide(value);
            return discountAmount;
        }
    }

    public BigDecimal calculateSubtotalAfterDiscount(BigDecimal subtotal, BigDecimal discountAmount) {
        BigDecimal subtotalafterdiscount;
        if (discountAmount == null) {
            return subtotalafterdiscount = subtotal;
        } else {
            subtotalafterdiscount = subtotal.subtract(discountAmount);
            return subtotalafterdiscount;
        }
    }

    public BigDecimal calculateVat(BigDecimal subtotalafterdiscount, BigDecimal vat) {
        BigDecimal vatAmount;
        if (vat == null) {
            vat = BigDecimal.ZERO;
            return vat;
        } else if (vat == BigDecimal.ZERO) {
            vatAmount = BigDecimal.ZERO;
            return vatAmount;
        } else {
            vatAmount = (subtotalafterdiscount.multiply(vat)).divide(value);
            return vatAmount;
        }
    }

    public BigDecimal calculateTotal(BigDecimal subtotalafterdiscount, BigDecimal vatAmount) {
        BigDecimal totalAmount;
        if (vatAmount == null) {
            return totalAmount = subtotalafterdiscount;
        } else {
            totalAmount = subtotalafterdiscount.add(vatAmount);
            return totalAmount;
        }
    }

//    public void finalSubTotalAmount(BigDecimal subtotal, BigDecimal sum){
//        sum = BigDecimal.ZERO;
//        
//    }
}
