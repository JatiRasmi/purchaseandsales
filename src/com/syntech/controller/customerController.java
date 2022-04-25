/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Customer;
import com.syntech.repository.customerRepository;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class customerController {
    private static customerRepository customerrepository;
    
    public static void main(String[] args){
        customerrepository = new customerRepository();
        Scanner sc = new Scanner(System.in);
        String choice;
        
        do{
            System.out.println("Enter 1 to Add customer");
            System.out.println("Enter 2 to Exit");
            choice = sc.next();
            switch(choice){
                case "1":
                    create();
                    break;
                case "2":
                    return;
                default: 
                    System.out.println("Invalid option");
                    break;
            }
        }while (!choice.equals("0"));
        
    } 
    
    public static void create(){
        Long id = null;
        String name = null;
        String address = null;
        String email = null;
        String contact = null;
        Scanner sc = new Scanner(System.in);
        while(id == null){
            System.out.println("Enter customer id:");
            String cid = sc.next();
            try{
                id = Long.parseLong(cid);
            }catch(Exception e){
                System.out.println("Error");
                id = null;
            }
        }
        
        while(name == null || name.isEmpty()){
            System.out.println("Enter customer name: ");
            name = sc.next();
            break;
        }
        
        while(address == null || address.isEmpty()){
            System.out.println("Enter customer address: ");
            address = sc.next();
            break;
        }
        
        while(email == null || email.isEmpty()){
            System.out.println("Enter customer email: ");
            email = sc.next();
            break;
        }
        
        while(contact == null || contact.isEmpty()){
            System.out.println("Enter customer contact: ");
            contact = sc.next();
            break;
        }
        
        Customer customer = new Customer(id,name,address,email,contact);
        customerrepository.create(customer);
        customerrepository.findAll().stream().forEach(x -> System.out.println(x));

//        System.out.println(customerrepository.findAll());
    } 
    
    public static void edit(){
        Long id = null;
        String name = null;
        String address = null;
        String email = null;
        String contact = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of customer to edit: ");
    }
    
}


