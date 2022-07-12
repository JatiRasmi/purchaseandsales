/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.converter;

import com.syntech.model.Unit;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.UnitRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesConverter(value = "unitConverter", forClass = Unit.class)
public class UnitConverter extends AbstractConverter<Unit> {
    
    @Inject
    private UnitRepository unitRepository;
    
    @Override
    protected AbstractRepository getRepository() {
        return unitRepository;
    }
    
}