/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

/**
 *
 * @author rasmi
 */
public class Calculation {

    public static Long calculateSubtotal(Long rate, Long quantity) {
        Long subtotal;
        subtotal = quantity * rate;
        return subtotal;
    }

    public static Long calculateDiscount(Long subtotal, Long discount) {
        Long discountAmount;
        discountAmount = (subtotal * discount) / 100;
        return discountAmount;
    }
    
    public static Long calculateSubtotalAfterDiscount(Long subtotal, Long discountAmount){
        Long subtotalafterdiscount;
        subtotalafterdiscount = subtotal - discountAmount;
        return subtotalafterdiscount;
    }
    public static Long calculateVat(Long subtotalafterdiscount, Long vat) {
        Long vatAmount;
        vatAmount = (subtotalafterdiscount * vat) / 100;
        return vatAmount;
    }

    public static Long calculateTotal(Long subtotalafterdiscount, Long vat) {
        Long totalAmount;
        totalAmount = subtotalafterdiscount + vat;
        return totalAmount;
    }
}
