/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.repository.CustomerRepository;
import com.syntech.repository.SupplierRepository;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class MainController {

    public static void main(String[] args) {
        CustomerController cc = new CustomerController();
        SupplierController sp = new SupplierController();
        CustomerRepository customerRepository = new CustomerRepository();
        SupplierRepository supplierRepository = new SupplierRepository();
        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.println("*******************");
        System.out.println("Choose Operation for");
        System.out.println("*******************");
        do {
            System.out.println("Enter 1 for customer");
            System.out.println("Enter 2 for Supplier ");
            System.out.println("Enter 3 to Exit");
            choice = sc.next();
            switch (choice) {
                case "1":
                    cc.customerOption(customerRepository);
                    break;
                case "2":
                    sp.supplierOption(supplierRepository);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!choice.equals("0"));

    }

}
