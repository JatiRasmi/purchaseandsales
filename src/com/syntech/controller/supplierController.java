/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Supplier;
import com.syntech.repository.supplierRepository;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class supplierController {
    private static supplierRepository supplierrepository;
    public static void main(String[] args){
        supplierrepository = new supplierRepository();
        String choice; 
        Scanner sc = new Scanner(System.in);
        System.out.println("Suppliers Operation");
        do{
            System.out.println("Enter 1 to create");
            System.out.println("Enter 2 to list");
            System.out.println("Enter 3 to delete");
            System.out.println("Enter 4 to edit");
            System.out.println("Enter 5 to exit");
            choice = sc.next();
            switch(choice){
                case "1" : 
                    create();
                    break;
                case "2":
                    list();
                    break;
                case "3" :
//                    delete();
                    break;
                case "4":
//                    edit();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }while(!choice.equals("0"));
    }
    
    public static void create(){
        String choice;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Enter 1 to add supplier");
            System.out.println("Enter 2 to exit");
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
        }while(!choice.equals("0"));
    }
    
    public static void createOption(){
        Long id = null;
        String name = null;
        String address = null;
        String email = null;
        String contact = null;
        String description = null;
        Scanner sc = new Scanner(System.in);
        while(id == null){
            System.out.println("Enter supplier id :");
            String sid = sc.nextLine();
            try{
                id = Long.parseLong(sid);
            }catch(Exception e){
                System.out.println("Error");
                id = null;
            }
        }
        while(name == null || name.isEmpty()){
            System.out.println("Enter Supplier name: ");
            name = sc.nextLine();
        }
        while(address == null || address.isEmpty()){
            System.out.println("Enter Supplier address: ");
            address = sc.nextLine();
        }
        while(email == null || email.isEmpty()){
            System.out.println("Enter supplier email: ");
            email = sc.nextLine();
        }
        while(contact == null || contact.isEmpty() ){
            System.out.println("Enter supplier contact: ");
            contact = sc.nextLine();
        }
        while(description == null || description.isEmpty()){
            System.out.println("Enter suppliers product description");
            description = sc.nextLine();
            break;
        }
        Supplier supplier = new Supplier(id, name, address, email, contact, description);
        supplierrepository.create(supplier);
//        System.out.println(supplierrepository.findAll());
        
    }
    public static void list(){
        System.out.println("Supplier's Info");
        System.out.println("----------------");
        supplierrepository
                .findAll()
                    .stream()
                        .forEach(
                                n -> System.out.println(n)
                        );
        System.out.println("____________________________________________________________________________");
    }
}










            