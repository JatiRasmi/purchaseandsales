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
    
    public void purchaseOrderDetailOption(PurchaseOrderDetailRepository purchaseorderdetailRepository, PurchaseOrderRepository purchaseorderRepository, ProductRepository productRepository){
        this.purchaseorderdetailRepository = purchaseorderdetailRepository;
        this.purchaseorderRepository = purchaseorderRepository;
        this.productRepository = productRepository;
              
        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.println("---------------Purchase Order Detail Operation---------------------");
        do{
            System.out.println("Enter 1 to create");
            System.out.println("Enter 2 to list");
            System.out.println("Enter 3 to delete");
            System.out.println("Enter 4 to edit");
            System.out.println("Enter 5 to exit");
            System.out.println("--------------------------------------------------------------------");
            choice = sc.next();
            switch(choice){
                case "1" : 
                    create();
                    break;
                case "2" :
                    list();
                    break;
                case "3":
//                    delete();
                    break;
                case "4" : 
//                    edit();
                    break;
                case "5":
                    return;
                default:
                    System.out.print("Invalid Option");
                    break;
            }
        }while(!choice.equals(0));
    }
        
    public static void create(){
        Long id = null;
        PurchaseOrder purchaseorder = null;
        Product product = null;
        Long quantity = null;
        Long rate = null;
        Long subtotal= null;
        Long discount = null;
        Long vat = null;
        Long totalamount = null;
        
        Calculation calculation = new Calculation();
        Scanner sc = new Scanner(System.in);
        while(id == null){
            System.out.println("Enter Purchase Order Detail Id : ");
            id = sc.nextLong();
        }
        System.out.println();
        List<PurchaseOrder> purchaseOrder = purchaseorderRepository.findAll();
        while(purchaseorder == null){
            System.out.println("_________Purchase Order List________ ");
            System.out.println(purchaseOrder);
            
            Long purchaseId = null;
            while(purchaseId == null){
                System.out.println("Enter Id of Purchase Order from list");
                purchaseId = sc.nextLong();
            }
            purchaseorder = purchaseorderRepository.findById(id);
        }
        
        
        System.out.println();
        
        System.out.println();
        List<Product> products = productRepository.findAll();
        while(product == null){
            System.out.println("_________Product List________");
            System.out.println(products);
            
            Long productId = null;
            while(productId == null){
                System.out.println("Enter Id of product from the list");
                productId = sc.nextLong();
            }
            product = productRepository.findById(id);
        }
        
        System.out.println();
        while(quantity == null){
            System.out.println("Enter Quantity : ");
            quantity = sc.nextLong();
        }
        while(rate == null){
            System.out.println("Enter Rate : ");
            rate = sc.nextLong();
        }
        
        Long sub = calculation.calculateSubtotal(rate, quantity);
        System.out.println("Sub Total \n " +sub );
        
        while(discount == null){
            System.out.println("Enter discount (%) : ");
            discount = sc.nextLong();
            System.out.println("Discount (%) : \n" + discount);
        }
        Long dis = calculation.calculateDiscount(sub, discount);
        System.out.println("Discount Amount : \n " + dis);
        
        Long subtotalafterdiscount = calculation.calculateSubtotalAfterDiscount(sub,dis);
        System.out.println("Sub Total After Discount : \n" + subtotalafterdiscount );
       
        while(vat == null){
            System.out.println("Enter VAT (%) : ");
            vat = sc.nextLong();
            System.out.println("Vat (%) : \n" + vat);
        }
        Long vats = calculation.calculateVat(sub, vat);
        System.out.println("VAT Amount : \n" + vats);
        
        Long total = calculation.calculateTotal(subtotalafterdiscount,vats);
        System.out.println("Total Amount : \n " + total);
        
        PurchaseOrderDetail purchaseorderdetail = new PurchaseOrderDetail(id,purchaseorder,product,quantity,rate,sub,discount,subtotalafterdiscount,dis,vat,vats,total);
        purchaseorderdetailRepository.create(purchaseorderdetail);
        System.out.println("------------------------------------------------------------");
        System.out.println("Operation completed successfully!!!");
        list();
    }
    
    public static void list() {
        System.out.println("__________________________________________________________________________________________________________________");
        System.out.println("   Purchase Order Detail List");
        System.out.println("----------------------------------");
        purchaseorderdetailRepository.findAll().forEach(x -> System.out.println(x +" \n \t"));
        System.out.println("___________________________________________________________________________________________________________________");
    }
}
