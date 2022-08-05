/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.User;
import com.syntech.repository.UserRepository;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rasmi
 */
@Startup
@Singleton
public class StartUp implements Serializable {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Inject
    private UserRepository userRepository;

    @PostConstruct
    public void createInitialUser() {
        String name = "admin";
        String email = "admin123@gmail.com";
        String password = "Admin@123";
        User u = userRepository.findByUsername(name);
        if (u == null) {
            logger.log(Level.WARNING, "User Table is empty !! User Doesn't Exists!!!");
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(hashPassword(password));
            userRepository.create(user);
        }
    }

    private String hashPassword(String pass) {
        String p = BCrypt.hashpw(pass, BCrypt.gensalt());
        return p;
    }
}
