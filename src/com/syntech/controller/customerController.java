/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import static com.syntech.controller.customerController.create;
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
            System.out.println("*******************");
            System.out.println("Customer Operation");
            System.out.println("*******************");
        do{
            System.out.println("Enter 1 to create");
            System.out.println("Enter 2 to list ");
            System.out.println("Enter 3 to delete");
            System.out.println("Enter 4 to edit");
            System.out.println("Enter 5 to Exit");
            choice = sc.next();
            switch(choice){
                case "1":
                    create();
                    break;
                case "2":
                    list();
                    break;
                case "3":
//                    list();
                    break;
                case "4":
//                    list();
                    break;
                case "5":
                    return;
                default: 
                    System.out.println("Invalid option");
                    break;
            }
        }while (!choice.equals("0"));
        
    } 
    
    public static void create(){
        String choice;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Enter 1 to Add customer");
            System.out.println("Enter 2 to Exit");
            choice = sc.next();
            switch(choice){
                case "1":
                 createOption();
              break;
                case "2":
                    return;
              default: 
                  System.out.println("Invalid option");
                  break;
            }
        }while (!choice.equals("0"));
        

    } 
    
    
    
    public static void list(){
        System.out.println("________________");
        System.out.println("Customer's Info");
        System.out.println("________________");
        System.out.println();
        System.out.println("*****************************************************************************");
//      System.out.println(customerrepository.findAll());
        customerrepository.findAll().stream().forEach(x -> System.out.println(x));
        System.out.println("*****************************************************************************");
    }
    
    
    
    
    
    
    public static void createOption(){
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
    }

}      