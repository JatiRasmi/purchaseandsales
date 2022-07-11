/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Unit;
import com.syntech.repository.UnitRepository;
import com.syntech.util.MessageUtill;
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
public class UnitController implements Serializable {

    private Unit unit;
    private List<Unit> unitList;

    @Inject
    private UnitRepository unitRepository;

    @Inject
    private MessageUtill messageUtill;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    @PostConstruct
    public void init() {
        this.unit = new Unit();
        this.unitList = unitRepository.findAll();
    }

    public void beforeCreate() {
        this.unit = new Unit();
    }

    public void create() {
        try {
            unitRepository.create(unit);
            this.unitList = unitRepository.findAll();
            messageUtill.showInfo("Unit Added Successfully", "Added Unit");
        } catch (Exception e) {
            e.printStackTrace();
            messageUtill.showError("Message", "Failed to create unit !!!");
        }

    }

    public void findAll() {
        unitRepository.findAll();
    }

    public void findById(Long id) {
        unitRepository.findById(id);
    }

    public void beforeEdit(Unit unit) {
        this.unit = unitRepository.findById(unit.getId());
    }

    public void edit() {
        try {
            unitRepository.edit(this.unit);
            this.unitList = unitRepository.findAll();
            messageUtill.showInfo("Unit Edited Successfully", "Edited Unit");
        } catch (Exception e) {
            e.printStackTrace();
            messageUtill.showError("Message", "Failed to update unit !!!");
        }
    }

    public void delete(Unit unit) {
        unitRepository.delete(unit);
        unitList = unitRepository.findAll();
        messageUtill.showInfo("Unit Deleted Successfully", "Deleted Unit");
    }
}
