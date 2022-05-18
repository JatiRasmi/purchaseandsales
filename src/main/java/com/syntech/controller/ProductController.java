/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Product;
import com.syntech.model.Unit;
import com.syntech.repository.ProductRepository;
import com.syntech.repository.UnitRepository;
import static com.syntech.util.Validator.isValidString;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class ProductController {

    private static ProductRepository productRepository;
    private static UnitRepository unitRepository;

    public void productOption(ProductRepository productRepository, UnitRepository unitRepository) {
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;

        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            System.out.println("-----------------Product Operations-----------------------------  ");
            System.out.println("Enter 1 to create : ");
            System.out.println("Enter 2 to list: ");
            System.out.println("Enter 3 to delete");
            System.out.println("Enter 4 to edit");
            System.out.println("Enter 5 to exit");
            System.out.println("------------------------------------------------------------------");
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
        Unit unit = null;
        String name = null;
        String description = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------Create Operation-----------------------");
//        while (id == null) {
//            System.out.println("Enter Product id:");
//            String pid = sc.next();
//            try {
//                id = Long.parseLong(pid);
//            } catch (NumberFormatException e) {
//                System.out.println("Error");
//                id = null;
//            }
//        }
        while (name == null || name.isEmpty()) {
            System.out.println("Enter Product name: ");
            name = sc.next();
            if (!isValidString(name)) {
                System.out.println("Invaild Product Name !!");
                name = null;
            }
        }
        while (description == null || description.isEmpty()) {
            System.out.println("Enter product description : ");
            description = sc.next();
            if (!isValidString(description)) {
                System.out.println("Invaild product description !!");
                description = null;
            }
        }

        List<Unit> units = unitRepository.findAll();
        while (unit == null) {
//           display unit list
            System.out.println(" _____Unit List_____ : ");
            System.out.println(units);

            Long unitId = null;
            while (unitId == null) {
                System.out.println("Enter Id for Unit from list : ");
                unitId = sc.nextLong();

            }
            unit = unitRepository.findById(unitId);
        }
        Product product = new Product(id, unit, name, description);
        product.setUnit(unit);
        productRepository.create(product);
        System.out.println("------------------------------------------------------------");
        System.out.println("Operation completed successfully!!!");
        list();
    }

    public static void list() {
        System.out.println("__________________________________________________________________________________________________________________");
        System.out.println("  Product List");
        System.out.println("-----------------");
        productRepository.findAll().forEach(x -> System.out.println(x));
        System.out.println("___________________________________________________________________________________________________________________");
    }

    public static void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------Delete Operation-----------------------");
        System.out.println("Enter Product Id: ");
        Long id = sc.nextLong();
        Product product = (Product) productRepository.findById(id);
        if (product == null) {
            System.out.println("Product ID " + id + " not found");
        } else {
            productRepository.delete(product);
            System.out.println("------------------------------------------------------------");
            System.out.println("Operation completed successfully!!!");
            list();
        }
    }

    public static void edit() {
        Long id = null;
        Unit unit = null;
        String name = null;
        String description = null;
        Scanner sc = new Scanner(System.in);
        List<Unit> units = unitRepository.findAll();
        System.out.println("------------------Edit Operation-----------------------");
        System.out.println("Enter product id to edit: ");
        id = sc.nextLong();
        Product product = (Product) productRepository.findById(id);
        if (product == null) {
            System.out.println("Product id: " + id + " not found");

        } else {
            while (name == null || name.isEmpty()) {
                System.out.println("Enter product: ");
                name = sc.next();
                if (!isValidString(name)) {
                    System.out.println("Invaild product !!");
                    name = null;
                }
            }
            while (description == null || description.isEmpty()) {
                System.out.println("Enter product description: ");
                description = sc.next();
                if (!isValidString(description)) {
                    System.out.println("Invaild product description !!");
                    description = null;
                }

            }
            while (unit == null) {
                System.out.println("Unit List : ");
                System.out.println(units);

                Long unitId = null;
                while (unitId == null) {
                    System.out.println("Enter Id for Unit from list : ");
                    unitId = sc.nextLong();

                }
                unit = unitRepository.findById(unitId);
            }

            Product prod = new Product(id, unit, name, description);
            productRepository.edit(prod);
            product.setUnit(unit);
            System.out.println("------------------------------------------------------------");
            System.out.println("Operation completed successfully!!!");
            list();
        }
    }
}
