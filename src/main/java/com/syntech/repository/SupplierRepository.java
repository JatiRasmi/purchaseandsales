/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Supplier;

/**
 *
 * @author rasmi
 */
public class SupplierRepository extends AbstractRepository<Supplier> {

    @Override
    public void edit(Supplier supplier) {
        super.findAll()
                .stream()
                .filter(n -> n.getId().equals(supplier.getId()))
                .forEach(s -> {
                    s.setName(supplier.getName());
                    s.setAddress(supplier.getAddress());
                    s.setEmail(supplier.getEmail());
                    s.setContact(supplier.getContact());
                    s.setDescription(supplier.getDescription());
                });
    }
}