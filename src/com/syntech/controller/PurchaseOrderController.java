/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.PurchaseOrder;
import com.syntech.model.Supplier;
import com.syntech.repository.PurchaseOrderRepository;
import com.syntech.repository.SupplierRepository;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class PurchaseOrderController {
    private static PurchaseOrderRepository purchaseorderRepository;
    private static SupplierRepository supplierRepository;

    public void purchaseorderOption(PurchaseOrderRepository purchaseorderRepository, SupplierRepository supplierRepository) {
        this.purchaseorderRepository = purchaseorderRepository;
        this.supplierRepository = supplierRepository;

        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.println("-------Purchase Operations--------- : ");
        do {
            System.out.println("Enter 1 to create : ");
            System.out.println("Enter 2 to list: ");
            System.out.println("Enter 3 to delete");
            System.out.println("Enter 4 to edit");
            System.out.println("Enter 5 to exit");
            choice = sc.next();
            switch (choice) {
                case "1":
                    create();
                    break;
                case "2":
                    list();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    edit();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid Option!!");
                    break;
            }
            System.out.println();
        } while (!choice.equals("0"));
    }

    public static void create() {
        Long id = null;
        Supplier supplier = null;
        String date = null;
        String expecteddeliverydate = null;
        
        Scanner sc = new Scanner(System.in);
        while (id == null) {
            System.out.println("Enter Purchase id: ");
            id = sc.nextLong();
        }
        while (date == null || date.isEmpty()) {
            System.out.println("Enter date: ");
            date = sc.next();
        }
        while (expecteddeliverydate == null || expecteddeliverydate.isEmpty()) {
            System.out.println("Enter expected delivery date : ");
            expecteddeliverydate = sc.next();
        }

        
        List<Supplier> suppliers = supplierRepository.findAll();
        while (supplier == null) {
            System.out.println("--------Supplier Info----------- : ");
            System.out.println(suppliers);

            Long supplierId = null;
            while (supplierId == null) {
                System.out.println("Enter Id for supplier from list : ");
                supplierId = sc.nextLong();

            }
            supplier = supplierRepository.findById(supplierId);
        }
        PurchaseOrder purchaseOrder = new PurchaseOrder(id, supplier, date, expecteddeliverydate);
        purchaseOrder.setSupplierid(supplier);
        purchaseorderRepository.create(purchaseOrder);
        list();
    }

    public static void list() {
        System.out.println("--------Purchase Order Info------------");
        purchaseorderRepository.findAll().forEach(x -> System.out.println(x));
        System.out.println("-----------END-------------------");
    }
    public static void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Purchase Order Id: ");
        Long id = sc.nextLong();
        PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseorderRepository.findById(id);
        if (purchaseOrder == null) {
            System.out.println("Purchase order ID " + id + " not found");
        } else {
            purchaseorderRepository.delete(purchaseOrder);
            System.out.println("Product id " + id + " deleted succesfully!!");
            list();
        }
    }
    public static void edit() {
        Long id = null;
        Supplier supplier = null;
        String date = null;
        String expecteddeliverydate = null;
        Scanner sc = new Scanner(System.in);
        List<Supplier> suppliers = supplierRepository.findAll();
        System.out.println("Enter purchase id to edit: ");
        id = sc.nextLong();
        PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseorderRepository.findById(id);
        if (purchaseOrder == null) {
            System.out.println("Purchase id: " + id + " not found");

        } else {
            while (date == null || date.isEmpty()) {
                System.out.println("Enter date: ");
                date = sc.next();
            }
            while (expecteddeliverydate == null || expecteddeliverydate.isEmpty()) {
                System.out.println("Enter expected delivery date: ");
                expecteddeliverydate = sc.next();
                

            }
            while (supplier == null) {
                System.out.println("Supplier's Info : ");
                System.out.println(suppliers);

                Long supplierId = null;
                while (supplierId == null) {
                    System.out.println("Enter Id for Supplier from list : ");
                    supplierId = sc.nextLong();

                }
                supplier = supplierRepository.findById(supplierId);
            }

            PurchaseOrder purchaseorder = new PurchaseOrder(id, supplier, date, expecteddeliverydate);
            purchaseorderRepository.edit(purchaseorder);
            purchaseorder.setSupplierid(supplier);
            System.out.println("Edited Successfully!");
            
            list();
        }
    }


}


