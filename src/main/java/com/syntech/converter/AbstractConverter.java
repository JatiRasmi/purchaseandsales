/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.converter;

import com.syntech.model.IEntity;
import com.syntech.repository.AbstractRepository;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author rasmi
 * @param <T>
 */
//@RequestScoped
//@FacesConverter(value = "unitConverter", forClass = Unit.class)
public abstract class AbstractConverter<T extends IEntity> implements Converter, Serializable{

    protected abstract AbstractRepository getRepository();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
         if (value == null || value.isEmpty() || value.length() == 0 || value.equals("")) {
            return null;
        }
        return getRepository().findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
            if (t == null || t.equals("")) {
            return "";
        }
        return ((T) t).getId().toString();
    }
    
}
