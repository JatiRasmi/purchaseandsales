/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Supplier;
import com.syntech.repository.SupplierRepository;
import com.syntech.util.MessageUtill;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rasmi
 */
@Named
@ViewScoped
public class SupplierController implements Serializable {

    private static final Logger logger = Logger.getLogger(SupplierController.class.getName());
    private Supplier supplier;
    private List<Supplier> supplierList;

    @Inject
    private SupplierRepository supplierRepository;

    @Inject
    MessageUtill messageUtill;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    @PostConstruct
    public void init() {
        this.supplier = new Supplier();
        this.supplierList = supplierRepository.findAll();
    }

    public void beforeCreate() {
        this.supplier = new Supplier();
    }

    public void create() {
        try {
            supplierRepository.create(supplier);
            this.supplierList = supplierRepository.findAll();
            messageUtill.showInfo("Supplier Added Successfully", "Supplier Added");
            logger.log(Level.INFO, " Supplier Created Successfully!!!:");
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "Supplier is null while deleting");
        }
    }

    public void beforeEdit(Supplier supplier) {
        this.supplier = supplierRepository.findById(supplier.getId());
    }

    public void edit() {
        try {
            supplierRepository.edit(this.supplier);
            this.supplierList = supplierRepository.findAll();
            messageUtill.showInfo("Supplier Edited Successfully", "Supplier Edited");
            logger.log(Level.INFO, " Supplier Edited Successfully!!!:");
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "Supplier is null while deleting");
        }
    }

    public void delete(Supplier supplier) {
        try {
            supplierRepository.delete(supplier);
            supplierList = supplierRepository.findAll();
            messageUtill.showInfo("Supplier Deleted Successfully", "Supplier Deleted");
            logger.log(Level.INFO, " Supplier Deleted Successfully!!!:");
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "Supplier is null while deleting");
        }
    }
}
