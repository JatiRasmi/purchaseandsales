/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import com.syntech.model.Unit;
import com.syntech.repository.UnitRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesConverter(value = "unitConverter", forClass = Unit.class)
public class UnitConverter implements Converter{

    @Inject
    UnitRepository unitRepository;
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
         if (value == null || value.isEmpty() || value.length() == 0 || value.equals("")) {
            return null;
        }
        return unitRepository.findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
            if (t == null || t.equals("")) {
            return "";
        }
        return ((Unit) t).getId().toString();
    }
    
}
