/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Product;

/**
 *
 * @author rasmi
 */
public class ProductRepository extends AbstractRepository<Product> {

    @Override
    public void edit(Product u) {
        super.findAll().stream()
                .filter(n -> n.getId().equals(u.getId()))
                .forEach((Product un) -> {
                    un.setName(u.getName());
                    un.setDescription(u.getDescription());
                    un.setUnitid(u.getUnitid());
                });
    }

}
