///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.controller;
//
//import com.syntech.model.Product;
//import com.syntech.model.SalesOrder;
//import com.syntech.model.SalesOrderDetail;
//import com.syntech.repository.ProductRepository;
//import com.syntech.repository.SalesOrderDetailRepository;
//import com.syntech.repository.SalesOrderRepository;
//import com.syntech.util.Calculation;
//import java.util.List;
//import java.util.Scanner;
//
///**
// *
// * @author rasmi
// */
//public class SalesOrderDetailController {
//
//    private static SalesOrderDetailRepository salesorderdetailRepository;
//    private static SalesOrderRepository salesorderRepository;
//    private static ProductRepository productRepository;
//
//    public void salesOrderDetailOption(SalesOrderDetailRepository salesorderdetailRepository, SalesOrderRepository salesorderRepository, ProductRepository productRepository) {
//        this.salesorderdetailRepository = salesorderdetailRepository;
//        this.salesorderRepository = salesorderRepository;
//        this.productRepository = productRepository;
//
//        Scanner sc = new Scanner(System.in);
//        String choice;
//        do {
//            System.out.println("---------------Sales Order Detail Operation---------------------");
//            System.out.println("Enter 1 to create");
//            System.out.println("Enter 2 to list");
//            System.out.println("Enter 3 to delete");
//            System.out.println("Enter 4 to edit");
//            System.out.println("Enter 5 to exit");
//            System.out.println("--------------------------------------------------------------------");
//            choice = sc.next();
//            switch (choice) {
//                case "1":
//                    create();
//                    break;
//                case "2":
//                    list();
//                    break;
//                case "3":
//                    delete();
//                    break;
//                case "4":
//                    edit();
//                    break;
//                case "5":
//                    return;
//                default:
//                    System.out.println("Invalid Option!!");
//                    break;
//            }
//            System.out.println();
//        } while (!choice.equals("0"));
//    }
//
//    public static void create() {
//        Long id = null;
//        SalesOrder salesorder = null;
//        Product product = null;
//        Long quantity = null;
//        Long rate = null;
//        Long subtotal = null;
//        Long discount = null;
//        Long vat = null;
//        Long totalamount = null;
//
//        Calculation calculation = new Calculation();
//        Scanner sc = new Scanner(System.in);
////        while (id == null) {
////            System.out.println("Enter sales order detail id:");
////            String sid = sc.next();
////            try {
////                id = Long.parseLong(sid);
////            } catch (NumberFormatException e) {
////                System.out.println("Error");
////                id = null;
////            }
////        }
//        while (quantity == null) {
//            System.out.println("Enter Quantity : ");
//            quantity = sc.nextLong();
//        }
//        while (rate == null) {
//            System.out.println("Enter Rate : ");
//            rate = sc.nextLong();
//        }
//
//        Long sub = calculation.calculateSubtotal(rate, quantity);
//        System.out.println("Sub Total \n " + sub);
//
//        while (discount == null) {
//            System.out.println("Enter discount (%) : ");
//            discount = sc.nextLong();
//            System.out.println("Discount (%) : \n" + discount);
//        }
//        Long dis = calculation.calculateDiscount(sub, discount);
//        System.out.println("Discount Amount : \n " + dis);
//
//        Long subtotalafterdiscount = calculation.calculateSubtotalAfterDiscount(sub, dis);
//        System.out.println("Sub Total After Discount : \n" + subtotalafterdiscount);
//
//        while (vat == null) {
//            System.out.println("Enter VAT (%) : ");
//            vat = sc.nextLong();
//            System.out.println("Vat (%) : \n" + vat);
//        }
//        Long vats = calculation.calculateVat(subtotalafterdiscount, vat);
//        System.out.println("VAT Amount : \n" + vats);
//
//        Long total = calculation.calculateTotal(subtotalafterdiscount, vats);
//        System.out.println("Total Amount : \n " + total);
//
//        System.out.println();
//        List<SalesOrder> purchaseOrder = salesorderRepository.findAll();
//        while (salesorder == null) {
//            System.out.println("_________Sales Order List________ ");
//            System.out.println(purchaseOrder);
//
//            Long salesId = null;
//            while (salesId == null) {
//                System.out.println("Enter Id of Sales Order from list");
//                salesId = sc.nextLong();
//            }
//            salesorder = salesorderRepository.findById(salesId);
//        }
//
//        System.out.println();
//
//        System.out.println();
//        List<Product> products = productRepository.findAll();
//        while (product == null) {
//            System.out.println("_________Product List________");
//            System.out.println(products);
//
//            Long productId = null;
//            while (productId == null) {
//                System.out.println("Enter Id of product from the list");
//                productId = sc.nextLong();
//            }
//            product = productRepository.findById(productId);
//        }
//
//        System.out.println();
//
//        SalesOrderDetail salesorderdetail = new SalesOrderDetail(id, salesorder, product, quantity, rate, sub, discount, dis, subtotalafterdiscount, vat, vats, total);
//        salesorderdetailRepository.create(salesorderdetail);
//        System.out.println("------------------------------------------------------------");
//        System.out.println("Operation completed successfully!!!");
//        list();
//    }
//
//    public static void list() {
//        System.out.println("__________________________________________________________________________________________________________________");
//        System.out.println("   Sales Order Detail List");
//        System.out.println("----------------------------------");
//        salesorderdetailRepository.findAll().forEach(x -> System.out.println(x + " \n \t"));
//        System.out.println("___________________________________________________________________________________________________________________");
//    }
//
//    public static void delete() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("------------------Delete Operation-----------------------");
//        System.out.println("Enter Sales Order Detail Id: ");
//        Long id = sc.nextLong();
//        SalesOrderDetail salesorderDetail = (SalesOrderDetail) salesorderdetailRepository.findById(id);
//        if (salesorderDetail == null) {
//            System.out.println("Sales order detail id " + id + "not found");
//        } else {
//            salesorderdetailRepository.delete(salesorderDetail);
//            System.out.println("------------------------------------------------------------");
//            System.out.println("Operation completed successfully!!!");
//            list();
//
//        }
//    }
//
//    public static void edit() {
//        Long id = null;
//        SalesOrder salesorder = null;
//        Product product = null;
//        Long quantity = null;
//        Long rate = null;
//        Long subtotal = null;
//        Long discount = null;
//        Long vat = null;
//        Long totalamount = null;
//
//        Calculation calculation = new Calculation();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("------------------Edit Operation-----------------------");
//        System.out.println("Enter sales order detail id to edit: ");
//        id = sc.nextLong();
//        SalesOrderDetail salesorderdetail = (SalesOrderDetail) salesorderdetailRepository.findById(id);
//        if (salesorderdetail == null) {
//            System.out.println("Sales order detail id: " + id + " not found");
//
//        } else {
//
//            while (quantity == null) {
//                System.out.println("Enter Quantity : ");
//                quantity = sc.nextLong();
//            }
//            while (rate == null) {
//                System.out.println("Enter Rate : ");
//                rate = sc.nextLong();
//            }
//
//            Long sub = calculation.calculateSubtotal(rate, quantity);
//            System.out.println("Sub Total \n " + sub);
//
//            while (discount == null) {
//                System.out.println("Enter discount (%) : ");
//                discount = sc.nextLong();
//                System.out.println("Discount (%) : \n" + discount);
//            }
//            Long dis = calculation.calculateDiscount(sub, discount);
//            System.out.println("Discount Amount : \n " + dis);
//
//            Long subtotalafterdiscount = calculation.calculateSubtotalAfterDiscount(sub, dis);
//            System.out.println("Sub Total After Discount : \n" + subtotalafterdiscount);
//
//            while (vat == null) {
//                System.out.println("Enter VAT (%) : ");
//                vat = sc.nextLong();
//                System.out.println("Vat (%) : \n" + vat);
//            }
//            Long vats = calculation.calculateVat(subtotalafterdiscount, vat);
//            System.out.println("VAT Amount : \n" + vats);
//
//            Long total = calculation.calculateTotal(subtotalafterdiscount, vats);
//            System.out.println("Total Amount : \n " + total);
//
//            System.out.println();
//            List<SalesOrder> salesOrder = salesorderRepository.findAll();
//            while (salesorder == null) {
//                System.out.println("_________Sales Order List________ ");
//                System.out.println(salesOrder);
//
//                Long salesId = null;
//                while (salesId == null) {
//                    System.out.println("Enter Id of Sales Order from list");
//                    salesId = sc.nextLong();
//                }
//                salesorder = salesorderRepository.findById(salesId);
//            }
//
//            System.out.println();
//            List<Product> products = productRepository.findAll();
//            while (product == null) {
//                System.out.println("_________Product List________");
//                System.out.println(products);
//
//                Long productId = null;
//                while (productId == null) {
//                    System.out.println("Enter Id of product from the list");
//                    productId = sc.nextLong();
//                }
//                product = productRepository.findById(productId);
//            }
//
//            SalesOrderDetail salesorderDetail = new SalesOrderDetail(id, salesorder, product, quantity, rate, sub, discount, dis, subtotalafterdiscount, vat, vats, total);
//            salesorderdetailRepository.edit(salesorderDetail);
//            salesorderDetail.setSalesorder(salesorder);
//            salesorderDetail.setProduct(product);
//            System.out.println("------------------------------------------------------------");
//            System.out.println("Operation completed successfully!!!");
//            list();
//
//        }
//    }
//}
