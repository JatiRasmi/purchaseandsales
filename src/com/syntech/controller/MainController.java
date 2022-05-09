/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.repository.CustomerRepository;
import com.syntech.repository.ProductRepository;
import com.syntech.repository.PurchaseOrderRepository;
import com.syntech.repository.SupplierRepository;
import com.syntech.repository.UnitRepository;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class MainController {

    public static void main(String[] args) {
        CustomerController cc = new CustomerController();
        SupplierController sp = new SupplierController();
        UnitController uc = new UnitController();
        ProductController pc = new ProductController();
        PurchaseOrderController poc = new PurchaseOrderController();
        
        CustomerRepository customerRepository = new CustomerRepository();
        SupplierRepository supplierRepository = new SupplierRepository();
        UnitRepository unitRepository = new UnitRepository();
        ProductRepository productRepository = new ProductRepository();
        PurchaseOrderRepository purchaseorderRepository = new PurchaseOrderRepository();
        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.println("*******************");
        System.out.println("Choose Operation for");
        System.out.println("*******************");
        do {
            System.out.println("Enter 1 for customer");
            System.out.println("Enter 2 for Supplier ");
            System.out.println("Enter 3 for Unit ");
            System.out.println("Enter 4 for Product");
            System.out.println("Enter 5 for Purchase Order");
            System.out.println("Enter 6 to Exit");
            choice = sc.next();
            switch (choice) {
                case "1":
                    cc.customerOption(customerRepository);
                    break;
                case "2":
                    sp.supplierOption(supplierRepository);
                    break;
                case "3":
                    uc.unitOption(unitRepository);
                    break;
                case "4":
                    pc.productOption(productRepository, unitRepository);
                    break;
                case "5" :
                    poc.purchaseorderOption(purchaseorderRepository, supplierRepository);
                case "6":
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!choice.equals("0"));

    }

}
