/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Unit;

/**
 *
 * @author rasmi
 */
public class UnitRepository extends AbstractRepository<Unit> {

    @Override
    public void edit(Unit u) {
        super.findAll().stream()
                .filter(n -> n.getId().equals(u.getId()))
                .forEach((Unit un) -> {
                    un.setName(u.getName());
                });
    }

}