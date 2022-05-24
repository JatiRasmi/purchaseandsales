/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.repository.CustomerRepository;
import com.syntech.repository.ProductRepository;
import com.syntech.repository.PurchaseOrderDetailRepository;
import com.syntech.repository.PurchaseOrderRepository;
import com.syntech.repository.SalesOrderDetailRepository;
import com.syntech.repository.SalesOrderRepository;
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
        PurchaseOrderDetailController podc = new PurchaseOrderDetailController();
        SalesOrderController soc = new SalesOrderController();
        SalesOrderDetailController sodc = new SalesOrderDetailController();
        DayBookController dbc = new DayBookController();
        
        CustomerRepository customerRepository = new CustomerRepository();
        SupplierRepository supplierRepository = new SupplierRepository();
        UnitRepository unitRepository = new UnitRepository();
        ProductRepository productRepository = new ProductRepository();
        PurchaseOrderRepository purchaseorderRepository = new PurchaseOrderRepository();
        PurchaseOrderDetailRepository purchaseorderdetailRepository = new PurchaseOrderDetailRepository();
        SalesOrderRepository salesorderRepository = new SalesOrderRepository();
        SalesOrderDetailRepository salesorderdetailRepository = new SalesOrderDetailRepository();

        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            System.out.println("------------------------Choose Operation for----------------------");
            System.out.println("Enter 1 for Unit ");
            System.out.println("Enter 2 for Product");
            System.out.println("Enter 3 for Supplier ");
            System.out.println("Enter 4 for Purchase Order");
            System.out.println("Enter 5 for Purchase Order Detail");
            System.out.println("Enter 6 for customer");
            System.out.println("Enter 7 for Sales Order");
            System.out.println("Enter 8 for Sales Order Detail");
            System.out.println("Enter 9 for Report");
            System.out.println("Enter 10 to exit");
            System.out.println("-------------------------------------------------------------------");
            choice = sc.next();
            switch (choice) {
                case "1":
                    uc.unitOption(unitRepository);
                    break;
                case "2":
                    pc.productOption(productRepository, unitRepository);
                    break;
                case "3":
                    sp.supplierOption(supplierRepository);
                    break;
                case "4":
                    poc.purchaseorderOption(purchaseorderRepository, supplierRepository, purchaseorderdetailRepository);
                    break;
                case "5":
                    podc.purchaseOrderDetailOption(purchaseorderdetailRepository, purchaseorderRepository, productRepository);
                    break;
                case "6":
                    cc.customerOption(customerRepository);
                    break;
                case "7":
                    soc.salesorderOption(salesorderRepository, customerRepository, salesorderdetailRepository);
                    break;
                case "8":
                    sodc.salesOrderDetailOption(salesorderdetailRepository, salesorderRepository, productRepository);
                    break;
                case "9":
                    dbc.dayBookOption(purchaseorderRepository, salesorderRepository);
                    break;
                case "10":
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!choice.equals("0"));

    }

}
