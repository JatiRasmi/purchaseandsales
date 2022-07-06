/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Supplier;
import com.syntech.repository.SupplierRepository;
import java.io.Serializable;
import java.util.List;
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

    private Supplier supplier;
    private List<Supplier> supplierList;

    @Inject
    private SupplierRepository supplierRepository;

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
        supplierRepository.create(supplier);
        this.supplierList = supplierRepository.findAll();
    }

    public void findAll() {
        supplierRepository.findAll();
    }

    public void findById(Long id){
        supplierRepository.findById(id);
    }
    
    public void beforeEdit(Supplier supplier) {
        this.supplier = supplierRepository.findById(supplier.getId());
    }

    public void edit() {
        supplierRepository.edit(this.supplier);
        this.supplierList = supplierRepository.findAll();
    }

    public void delete(Supplier supplier) {
        supplierRepository.delete(supplier);
        supplierList = supplierRepository.findAll();

    }
}

