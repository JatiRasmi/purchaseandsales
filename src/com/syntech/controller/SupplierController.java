/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Supplier;
import com.syntech.repository.SupplierRepository;
import static com.syntech.util.Validator.isValidEmail;
import static com.syntech.util.Validator.isValidNumber;
import static com.syntech.util.Validator.isValidString;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class SupplierController {

    private static SupplierRepository supplierRepository;

    public void supplierOption(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        String choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Suppliers Operation");
        do {
            System.out.println("Enter 1 to create");
            System.out.println("Enter 2 to list");
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
                    System.out.println("Invalid Option");
                    break;
            }
        } while (!choice.equals("0"));
    }

    public static void create() {
        Long id = null;
        String name = null;
        String address = null;
        String email = null;
        String contact = null;
        String description = null;
        Scanner sc = new Scanner(System.in);
        while (id == null) {
            System.out.println("Enter supplier id :");
            String sid = sc.nextLine();
            try {
                id = Long.parseLong(sid);
            } catch (Exception e) {
                System.out.println("Error");
                id = null;
            }
        }
        while (name == null || name.isEmpty()) {
            System.out.println("Enter Supplier name: ");
            name = sc.next();
            if(!isValidString(name)){
                System.out.println("Invaild Name !!");
                name = null;
            }
        }
        while (address == null || address.isEmpty()) {
            System.out.println("Enter Supplier address: ");
            address = sc.next();
            if(!isValidString(address)){
                System.out.println("Invaild address !!");
                address = null;
            }
        }
        while (email == null || email.isEmpty()) {
            System.out.println("Enter supplier email: ");
            email = sc.next();
            if(!isValidEmail(email)){
                System.out.println("Invaild Email !!");
                email = null;
            }
        }
        while (contact == null || contact.isEmpty()) {
            System.out.println("Enter supplier contact: ");
            contact = sc.next();
            if(!isValidNumber(contact)){
                System.out.println("Invaild contact !!");
                contact = null;
            }
        }
        while (description == null || description.isEmpty()) {
            System.out.println("Enter suppliers product description");
            description = sc.next();
            if(!isValidString(description)){
                System.out.println("Invaild description !!");
                description = null;
            }
        }
        Supplier supplier = new Supplier(id, name, address, email, contact, description);
        supplierRepository.create(supplier);
        list();

    }

    public static void list() {
        System.out.println("Supplier's Info");
        System.out.println("----------------");
        supplierRepository
                .findAll()
                .stream()
                .forEach(
                        n -> System.out.println(n)
                );
        System.out.println("____________________________________________________________________________");
    }

    public static void delete() {
        System.out.println("Enter suppliers id: ");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();
        Supplier supplier = (Supplier) supplierRepository.findById(id);
        if (supplier == null) {
            System.out.println("Supplier id " + id + "not found");
        } else {
            supplierRepository.delete(supplier);
            list();
        }
    }

    public static void edit() {
        Long id = null;
        String name = null;
        String address = null;
        String email = null;
        String contact = null;
        String description = null;
        System.out.println("Enter supplier's id:");
        Scanner sc = new Scanner(System.in);
        id = sc.nextLong();
        Supplier supplier = (Supplier) supplierRepository.findById(id);
        if (supplier == null) {
            System.out.println("Supplier id" + id + "not found");
        } else {
            while (name == null || name.isEmpty()) {
                System.out.println("Enter Supplier name: ");
                name = sc.next();
                if(!isValidString(name)){
                    System.out.println("Invaild Name !!");
                    name = null;
                }
            }
            while (address == null || address.isEmpty()) {
                System.out.println("Enter Supplier address: ");
                address = sc.next();
                if(!isValidString(address)){
                    System.out.println("Invaild address !!");
                    address = null;
                }
            }
            while (email == null || email.isEmpty()) {
                System.out.println("Enter supplier email: ");
                email = sc.next();
                if(!isValidEmail(email)){
                    System.out.println("Invaild Email !!");
                    email = null;
                }
            }
            while (contact == null || contact.isEmpty()) {
                System.out.println("Enter supplier contact: ");
                contact = sc.next();
                if(!isValidNumber(contact)){
                    System.out.println("Invaild contact !!");
                    contact = null;
                }
            }
            while (description == null || description.isEmpty()) {
                System.out.println("Enter suppliers product description");
                description = sc.next();
                if(!isValidString(description)){
                    System.out.println("Invaild description !!");
                    description = null;
                }
            }
        }
        Supplier supply = new Supplier(id, name, address, email, contact, description);
        supplierRepository.edit(supply);
        list();
    }
}
