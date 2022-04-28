/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rasmi
 */
public abstract class Validator {

    public static boolean isValidateString(String str) {
        String regex = "^[A-Za-z]+$";
        Pattern p = Pattern.compile(regex);  //compile regex
        Matcher m = p.matcher(str); //matcher() method find matching between name and regular expression
        return m.matches(); //return if name matches regex
    }

    public static boolean isValidateNumber(String str) {
        String regex = "[9][0-9]{9}";          //start with 9 and number of digit 10 
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    public static boolean isValidateEmail(String str) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  //rasmi@gmail.com  //rasmi01@gmail.com
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
