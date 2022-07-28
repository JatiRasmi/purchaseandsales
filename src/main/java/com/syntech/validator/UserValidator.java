/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.validator;

import com.syntech.model.User;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.UserRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.validator.FacesValidator;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */
@RequestScoped
@FacesValidator("userValidator")
public class UserValidator extends AbstractValidator<User> {

    @Inject
    private UserRepository UserRepository;

    @Override
    protected AbstractRepository getRepository() {
        return UserRepository;
    }

}
