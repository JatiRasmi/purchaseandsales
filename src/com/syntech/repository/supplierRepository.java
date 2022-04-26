/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Supplier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmi
 */
public class supplierRepository {

    private List<Supplier> supplierList;

    public supplierRepository() {
        supplierList = new ArrayList();
    }

    public void create(Supplier supplierList) {
        this.supplierList.add(supplierList);
    }

    public List<Supplier> findAll(){
        return  supplierList;
    }
    
    public  Supplier findById(Long id){
        for(Supplier s: supplierList)
                if(s.getId().equals(id))
                    return s;
        return null;
    }
    public void delete(Supplier supplierList){
        this.supplierList.remove(supplierList);
    }
    
 }
