/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import com.syntech.model.Unit;
import com.syntech.repository.UnitRepository;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesValidator("uniqueColumnValidator")
public class UniqueColumnValidator implements Validator, Serializable {

    @Inject
    private UnitRepository unitRepository;
    
    /**
     * generic unique constraint validator for AbstractBaseEntity entities<br />
     * requires the following additional attributes on the form element
     * ("<f:attribute>"):<br />
     * - "currentEntity" the entity instance (used for getting the class and
     * guid)<br />
     * - "uniqueColumn" the column where the new value will be checked for
     * uniqueness
     */
    @Override
    public void validate(final FacesContext context, final UIComponent comp, final Object newValue) throws ValidatorException {

        Unit currentEntity = (Unit) comp.getAttributes().get("currentEntity");
        String uniqueColumn = (String) comp.getAttributes().get("uniqueColumn");

        boolean isValid = false;
        
        isValid = unitRepository.isUnique(currentEntity, uniqueColumn, newValue);

        if (!isValid) {
            FacesMessage msg = new FacesMessage("must be unique: " + uniqueColumn);
            context.addMessage(comp.getClientId(context), msg);
            throw new ValidatorException(msg);
        }
    }
}
