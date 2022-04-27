/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

//import static com.syntech.controller.CustomerController.create;
import com.syntech.model.Customer;
import com.syntech.repository.CustomerRepository;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class CustomerController {

    private static CustomerRepository customerRepository;

    public void customerOption(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.println("*******************");
        System.out.println("Customer Operation");
        System.out.println("*******************");
        do {
            System.out.println("Enter 1 to create");
            System.out.println("Enter 2 to list ");
            System.out.println("Enter 3 to delete");
            System.out.println("Enter 4 to edit");
            System.out.println("Enter 5 to Exit");
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
                    System.out.println("Invalid option");
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
        Scanner sc = new Scanner(System.in);
        while (id == null) {
            System.out.println("Enter customer id:");
            String cid = sc.next();
            try {
                id = Long.parseLong(cid);
            } catch (Exception e) {
                System.out.println("Error");
                id = null;
            }
        }
        while (name == null || name.isEmpty()) {
            System.out.println("Enter customer name: ");
            name = sc.next();
            break;
        }
        while (address == null || address.isEmpty()) {
            System.out.println("Enter customer address: ");
            address = sc.next();
            break;
        }
        while (email == null || email.isEmpty()) {
            System.out.println("Enter customer email: ");
            email = sc.next();
            break;
        }
        while (contact == null || contact.isEmpty()) {
            System.out.println("Enter customer contact: ");
            contact = sc.next();
            break;
        }
        Customer customer = new Customer(id, name, address, email, contact);
        customerRepository.create(customer);
        list();
    }

    public static void list() {
        System.out.println("-----------------");
        System.out.println("Customer's Info");
        System.out.println("-----------------");
        System.out.println();
        System.out.println("*****************************************************************************");
        customerRepository.findAll().stream().forEach(x -> System.out.println(x));
        System.out.println("*****************************************************************************");
        System.out.println();
    }

    public static void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer's Id: ");
        Long id = sc.nextLong();
        Customer customer = (Customer) customerRepository.findById(id);
        if (customer == null) {
            System.out.println("Customer's ID " + id + " not found");
        } else {
            customerRepository.delete(customer);
            System.out.println("Customer of id " + id + " deleted succesfully!!");
            list();
        }
    }

    public static void edit() {
        Long id = null;
        String name = null;
        String address = null;
        String email = null;
        String contact = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of customer to edit: ");
        id = sc.nextLong();
        Customer customer = (Customer) customerRepository.findById(id);
        if (customer == null) {
            System.out.println("Customer with id: " + id + " not found");

        } else {
            while (name == null || name.isEmpty()) {
                System.out.println("Enter customer name");
                name = sc.next();
            }
            while (address == null || address.isEmpty()) {
                System.out.println("Enter customer address");
                address = sc.next();
            }
            while (email == null || email.isEmpty()) {
                System.out.println("Enter customer email");
                email = sc.next();
            }
            while (contact == null || contact.isEmpty()) {
                System.out.println("Enter customer contact");
                contact = sc.next();
            }

            Customer cust = new Customer(id, name, address, email, contact);
            customerRepository.edit(cust);
            System.out.println("Edited Successfully!");
            list();
        }
    }

}
