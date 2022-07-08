/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.validator;

import com.syntech.model.Unit;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.UnitRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.validator.FacesValidator;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesValidator("unitValidator")
public class UnitValidator extends AbstractValidator<Unit> {
    
    @Inject
    private UnitRepository unitRepository;
    
    @Override
    protected AbstractRepository getRepository() {
        return unitRepository;
    }
    
}
