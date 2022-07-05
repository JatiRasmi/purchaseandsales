///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.controller;
//
//import com.syntech.model.Customer;
//import com.syntech.model.SalesOrder;
//import com.syntech.model.SalesOrderDetail;
//import com.syntech.repository.CustomerRepository;
//import com.syntech.repository.SalesOrderDetailRepository;
//import com.syntech.repository.SalesOrderRepository;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Scanner;
//
///**
// *
// * @author rasmi
// */
//public class SalesOrderController {
//
//    private static SalesOrderRepository salesorderRepository;
//    private static CustomerRepository customerRepository;
//    private static SalesOrderDetailRepository salesorderdetailRepository;
//
//    public void salesorderOption(SalesOrderRepository salesorderRepository, CustomerRepository customerRepository, SalesOrderDetailRepository salesorderdetailRepository) {
//        this.salesorderRepository = salesorderRepository;
//        this.customerRepository = customerRepository;
//        this.salesorderdetailRepository = salesorderdetailRepository;
//
//        Scanner sc = new Scanner(System.in);
//        String choice;
//        do {
//            System.out.println("----------------------Sales Operations--------------------------");
//            System.out.println("Enter 1 to create : ");
//            System.out.println("Enter 2 to list: ");
//            System.out.println("Enter 3 to delete");
//            System.out.println("Enter 4 to edit");
//            System.out.println("Enter 5 to list detail of sales order");
//            System.out.println("Enter 6 to exit");
//            System.out.println("-------------------------------------------------------------------");
//            choice = sc.next();
//            switch (choice) {
//                case "1":
//                    create();
//                    break;
//                case "2":
////                    list();
//                    break;
//                case "3":
////                    delete();
//                    break;
//                case "4":
////                    edit();
//                    break;
//                case "5":
////                    listAll();
//                case "6":
//                    return;
//                default:
//                    System.out.print("Invalid Option");
//                    break;
//            }
//
//            System.out.println();
//        } while (!choice.equals("0"));
//    }
//
//    public static void create() {
//        Long id = null;
//        Customer customer = null;
//        String date = null;
//        BigDecimal totalamount = null;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("------------------Create Operation-----------------------");
//        while (date == null || date.isEmpty()) {
//            System.out.println("Enter date: ");
//            date = sc.next();
//        }
//
////        List<Customer> customers = customerRepository.findAll();
////        while (customer == null) {
////            System.out.println("_________Customer List___________ : ");
////            System.out.println(customers);
////
////            Long customerId = null;
////            while (customerId == null) {
////                System.out.println("Enter Id for customer from list : ");
////                customerId = sc.nextLong();
////
////            }
////            customer = customerRepository.findById(customerId);
////        }
//        SalesOrder salesOrder = new SalesOrder(id, customer, date, totalamount);
//        salesOrder.setCustomer(customer);
//        salesOrder.setTotalAmount(totalamount);
//        salesorderRepository.create(salesOrder);
//        System.out.println("------------------------------------------------------------");
//        System.out.println("Operation completed successfully!!!");
////        list();
//    }
//
////    public static void list() {
////        System.out.println("__________________________________________________________________________________________________________________");
////        System.out.println("   Sales Order List");
////        System.out.println("----------------------------");
////        salesorderRepository.findAll().forEach(x -> System.out.println(x));
////        System.out.println("___________________________________________________________________________________________________________________");
////    }
////
////    public static void delete() {
////        Scanner sc = new Scanner(System.in);
////        System.out.println("------------------Delete Operation-----------------------");
////        System.out.println("Enter Sales Order Id: ");
////        Long id = sc.nextLong();
////        SalesOrder salesOrder = (SalesOrder) salesorderRepository.findById(id);
////        if (salesOrder == null) {
////            System.out.println("Sales order ID " + id + " not found");
////        } else {
////            salesorderRepository.delete(salesOrder);
////            System.out.println("------------------------------------------------------------");
////            System.out.println("Operation completed successfully!!!");
////            list();
////        }
////    }
////
////    public static void edit() {
////        Long id = null;
////        Customer customer = null;
////        String date = null;
////        BigDecimal totalamount = null;
////        Scanner sc = new Scanner(System.in);
////        List<Customer> customers = customerRepository.findAll();
////        System.out.println("------------------Edit Operation-----------------------");
////        System.out.println("Enter Sales id to edit: ");
////        id = sc.nextLong();
////        SalesOrder salesOrder = (SalesOrder) salesorderRepository.findById(id);
////        if (salesOrder == null) {
////            System.out.println("Sales id: " + id + " not found");
////
////        } else {
////            while (date == null || date.isEmpty()) {
////                System.out.println("Enter date: ");
////                date = sc.next();
////            }
////            while (customer == null) {
////                System.out.println("Customer's List : ");
////                System.out.println(customers);
////
////                Long customerId = null;
////                while (customerId == null) {
////                    System.out.println("Enter Id for Customer from list : ");
////                    customerId = sc.nextLong();
////
////                }
////                customer = customerRepository.findById(customerId);
////            }
////
////            SalesOrder salesorder = new SalesOrder(id, customer, date, totalamount);
////            salesorderRepository.edit(salesorder);
////            salesorder.setCustomer(customer);
////            salesOrder.setTotalAmount(totalamount);
////            System.out.println("------------------------------------------------------------");
////            System.out.println("Operation completed successfully!!!");
////            list();
////        }
////    }
////
////    public static void listAll() {
////        salesorderdetailRepository.findAll().forEach(x -> System.out.println(x));
////    }
//
//}
