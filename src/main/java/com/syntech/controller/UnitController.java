/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Unit;
import com.syntech.repository.UnitRepository;
import static com.syntech.util.Validator.isValidString;
import java.util.Scanner;

/**
 *
 * @author rasmi
 */
public class UnitController {

    private static UnitRepository unitRepository;

    public void unitOption(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            System.out.println("-------------------------Unit Operations------------------------- ");
            System.out.println("Enter 1 to create : ");
            System.out.println("Enter 2 to list : ");
            System.out.println("Enter 3 to delete : ");
            System.out.println("Enter 4 to edit : ");
            System.out.println("Enter 5 to exit: ");
            System.out.println("-------------------------------------------------------------------");
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
        } while (!choice.equals("0"));
    }

    public static void create() {
        Long id = null;
        String name = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------Create Operation-----------------------");
//        while (id == null) {
//            System.out.println("Enter Unit id:");
//            String uid = sc.next();
//            try {
//                id = Long.parseLong(uid);
//            } catch (NumberFormatException e) {
//                System.out.println("Error");
//                id = null;
//            }
//        }
        while (name == null || name.isEmpty()) {
            System.out.println("Enter unit: ");
            name = sc.next();
            if (!isValidString(name)) {
                System.out.println("Invaild Unit Name !!");
                name = null;
            }
        }
        Unit unit = new Unit(id, name);
        unitRepository.create(unit);
        System.out.println("------------------------------------------------------------");
        System.out.println("Operation completed successfully!!!");
        list();
    }

    public static void list() {
        System.out.println("__________________________________________________________________________________________________________________");
        System.out.println("   Unit List");
        System.out.println("----------------");
        unitRepository.findAll().stream().forEach(x -> System.out.println(x));
        System.out.println("___________________________________________________________________________________________________________________");
    }

    public static void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------Delete Operation--------------------");
        System.out.println("Enter Unit Id: ");
        Long id = sc.nextLong();
        Unit unit = (Unit) unitRepository.findById(id);
        if (unit == null) {
            System.out.println("Unit ID " + id + " not found");
        } else {
            unitRepository.delete(unit);
            System.out.println("------------------------------------------------------------");
            System.out.println("Operation completed successfully!!!");
            list();
        }
    }

    public static void edit() {
        Long id = null;
        String unitname = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------Edit Operation--------------------");
        System.out.println("Enter unit id to edit: ");
        id = sc.nextLong();
        Unit unit = (Unit) unitRepository.findById(id);
        if (unit == null) {
            System.out.println("Unit id: " + id + " not found");

        } else {
            while (unitname == null || unitname.isEmpty()) {
                System.out.println("Enter unit: ");
                unitname = sc.next();
                if (!isValidString(unitname)) {
                    System.out.println("Invaild unit !!");
                    unitname = null;
                }
            }

            Unit un = new Unit(id, unitname);
            unitRepository.edit(un);
            System.out.println("------------------------------------------------------------");
            System.out.println("Operation completed successfully!!!");
            list();
        }
    }

}
