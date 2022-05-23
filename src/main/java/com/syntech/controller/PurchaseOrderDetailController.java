/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Product;
import com.syntech.model.PurchaseOrder;
import com.syntech.model.PurchaseOrderDetail;
import com.syntech.repository.ProductRepository;
import com.syntech.repository.PurchaseOrderDetailRepository;
import com.syntech.repository.PurchaseOrderRepository;
import com.syntech.util.Calculation;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class PurchaseOrderDetailController {

    private static PurchaseOrderDetailRepository purchaseorderdetailRepository;
    private static PurchaseOrderRepository purchaseorderRepository;
    private static ProductRepository productRepository;

    public void purchaseOrderDetailOption(PurchaseOrderDetailRepository purchaseorderdetailRepository, PurchaseOrderRepository purchaseorderRepository, ProductRepository productRepository) {
        this.purchaseorderdetailRepository = purchaseorderdetailRepository;
        this.purchaseorderRepository = purchaseorderRepository;
        this.productRepository = productRepository;

        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            System.out.println("---------------Purchase Order Detail Operation---------------------");
            System.out.println("Enter 1 to create");
            System.out.println("Enter 2 to list");
            System.out.println("Enter 3 to delete");
            System.out.println("Enter 4 to edit");
            System.out.println("Enter 5 to exit");
            System.out.println("--------------------------------------------------------------------");
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
        PurchaseOrder purchaseorder = null;
        Product product = null;
        Long quantity = null;
        Long rate = null;
        Long subtotal = null;
        Long discount = null;
        Long vat = null;
        Long totalamount = null;

        Calculation calculation = new Calculation();
        Scanner sc = new Scanner(System.in);
//        while (id == null) {
//            System.out.println("Enter purchase order detail id:");
//            String pid = sc.next();
//            try {
//                id = Long.parseLong(pid);
//            } catch (NumberFormatException e) {
//                System.out.println("Error");
//                id = null;
//            }
//        }
        while (quantity == null) {
            System.out.println("Enter Quantity : ");
            quantity = sc.nextLong();
        }
        while (rate == null) {
            System.out.println("Enter Rate : ");
            rate = sc.nextLong();
        }

        Long sub = calculation.calculateSubtotal(rate, quantity);
        System.out.println("Sub Total \n " + sub);

        while (discount == null) {
            System.out.println("Enter discount (%) : ");
            discount = sc.nextLong();
            System.out.println("Discount (%) : \n" + discount);
        }
        Long dis = calculation.calculateDiscount(sub, discount);
        System.out.println("Discount Amount : \n " + dis);

        Long subtotalafterdiscount = calculation.calculateSubtotalAfterDiscount(sub, dis);
        System.out.println("Sub Total After Discount : \n" + subtotalafterdiscount);

        while (vat == null) {
            System.out.println("Enter VAT (%) : ");
            vat = sc.nextLong();
            System.out.println("Vat (%) : \n" + vat);
        }
        Long vats = calculation.calculateVat(subtotalafterdiscount, vat);
        System.out.println("VAT Amount : \n" + vats);

        Long total = calculation.calculateTotal(subtotalafterdiscount, vats);
        System.out.println("Total Amount : \n " + total);

        System.out.println();
        List<PurchaseOrder> purchaseOrder = purchaseorderRepository.findAll();
        while (purchaseorder == null) {
            System.out.println("_________Purchase Order List________ ");
            System.out.println(purchaseOrder);

            Long purchaseId = null;
            while (purchaseId == null) {
                System.out.println("Enter Id of Purchase Order from list");
                purchaseId = sc.nextLong();
            }
            purchaseorder = purchaseorderRepository.findById(purchaseId);
        }

        System.out.println();

        System.out.println();
        List<Product> products = productRepository.findAll();
        while (product == null) {
            System.out.println("_________Product List________");
            System.out.println(products);

            Long productId = null;
            while (productId == null) {
                System.out.println("Enter Id of product from the list");
                productId = sc.nextLong();
            }
            product = productRepository.findById(productId);
        }

        System.out.println();

        PurchaseOrderDetail purchaseorderdetail = new PurchaseOrderDetail(id, purchaseorder, product, quantity, rate, sub, discount, dis, subtotalafterdiscount, vat, vats, total);
        purchaseorderdetailRepository.create(purchaseorderdetail);
        System.out.println("------------------------------------------------------------");
        System.out.println("Operation completed successfully!!!");
//        list();
    }

    public static void list() {
        System.out.println("__________________________________________________________________________________________________________________");
        System.out.println("   Purchase Order Detail List");
        System.out.println("----------------------------------");
        purchaseorderdetailRepository.findAll().forEach(x -> System.out.println(x + " \n \t"));
        System.out.println("___________________________________________________________________________________________________________________");
    }

    public static void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------Delete Operation-----------------------");
        System.out.println("Enter Purchase Order Detail Id: ");
        Long id = sc.nextLong();
        PurchaseOrderDetail purchaseorderDetail = (PurchaseOrderDetail) purchaseorderdetailRepository.findById(id);
        if (purchaseorderDetail == null) {
            System.out.println("Purchase order detail id " + id + "not found");
        } else {
            purchaseorderdetailRepository.delete(purchaseorderDetail);
            System.out.println("------------------------------------------------------------");
            System.out.println("Operation completed successfully!!!");
//            list();

        }
    }

    public static void edit() {
        Long id = null;
        PurchaseOrder purchaseorder = null;
        Product product = null;
        Long quantity = null;
        Long rate = null;
        Long subtotal = null;
        Long discount = null;
        Long vat = null;
        Long totalamount = null;

        Calculation calculation = new Calculation();
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------Edit Operation-----------------------");
        System.out.println("Enter purchase order detail id to edit: ");
        id = sc.nextLong();
        PurchaseOrderDetail purchaseorderDetail = (PurchaseOrderDetail) purchaseorderdetailRepository.findById(id);
        if (purchaseorderDetail == null) {
            System.out.println("Purchase order detail id: " + id + " not found");

        } else {

            while (quantity == null) {
                System.out.println("Enter Quantity : ");
                quantity = sc.nextLong();
            }
            while (rate == null) {
                System.out.println("Enter Rate : ");
                rate = sc.nextLong();
            }

            Long sub = calculation.calculateSubtotal(rate, quantity);
            System.out.println("Sub Total \n " + sub);

            while (discount == null) {
                System.out.println("Enter discount (%) : ");
                discount = sc.nextLong();
                System.out.println("Discount (%) : \n" + discount);
            }
            Long dis = calculation.calculateDiscount(sub, discount);
            System.out.println("Discount Amount : \n " + dis);

            Long subtotalafterdiscount = calculation.calculateSubtotalAfterDiscount(sub, dis);
            System.out.println("Sub Total After Discount : \n" + subtotalafterdiscount);

            while (vat == null) {
                System.out.println("Enter VAT (%) : ");
                vat = sc.nextLong();
                System.out.println("Vat (%) : \n" + vat);
            }
            Long vats = calculation.calculateVat(subtotalafterdiscount, vat);
            System.out.println("VAT Amount : \n" + vats);

            Long total = calculation.calculateTotal(subtotalafterdiscount, vats);
            System.out.println("Total Amount : \n " + total);

            System.out.println();
            List<PurchaseOrder> purchaseOrder = purchaseorderRepository.findAll();
            while (purchaseorder == null) {
                System.out.println("_________Purchase Order List________ ");
                System.out.println(purchaseOrder);

                Long purchaseId = null;
                while (purchaseId == null) {
                    System.out.println("Enter Id of Purchase Order from list");
                    purchaseId = sc.nextLong();
                }
                purchaseorder = purchaseorderRepository.findById(purchaseId);
            }

            System.out.println();
            List<Product> products = productRepository.findAll();
            while (product == null) {
                System.out.println("_________Product List________");
                System.out.println(products);

                Long productId = null;
                while (productId == null) {
                    System.out.println("Enter Id of product from the list");
                    productId = sc.nextLong();
                }
                product = productRepository.findById(productId);
            }

            PurchaseOrderDetail purchaseorderdetail = new PurchaseOrderDetail(id, purchaseorder, product, quantity, rate, sub, discount, dis, subtotalafterdiscount, vat, vats, total);
            purchaseorderdetailRepository.edit(purchaseorderdetail);
            purchaseorderdetail.setPurchaseorder(purchaseorder);
            purchaseorderdetail.setProduct(product);
            System.out.println("------------------------------------------------------------");
            System.out.println("Operation completed successfully!!!");
//            list();

        }
    }

}
