/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.validator;

import com.syntech.model.IEntity;
import com.syntech.repository.AbstractRepository;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author rasmi
 * @param <T>
 */
public abstract class AbstractValidator<T extends IEntity> implements Validator, Serializable {

    protected abstract AbstractRepository getRepository();

    /**
     * generic unique constraint validator for AbstractBaseEntity entities<br />
     * requires the following additional attributes on the form element
     * ("<f:attribute>"):<br />
     * - "currentEntity" the entity instance (used for getting the class and
     * guid)<br />
     * - "uniqueColumn" the column where the new value will be checked for
     * uniqueness
     *
     * @param context
     * @param comp
     * @param newValue
     */
    @Override
    public void validate(final FacesContext context, final UIComponent comp, final Object newValue) throws ValidatorException {

        T currentEntity = (T) comp.getAttributes().get("currentEntity");
        String uniqueColumn = (String) comp.getAttributes().get("uniqueColumn");
        Long id = currentEntity.getId();
        boolean isValid = false;

        isValid = getRepository().isUnique(currentEntity, uniqueColumn, newValue, id);
        if (!isValid) {
            FacesMessage msg = new FacesMessage(uniqueColumn + " must be unique! ");
            context.addMessage(comp.getClientId(context), msg);
            throw new ValidatorException(msg);
        }
    }
}
