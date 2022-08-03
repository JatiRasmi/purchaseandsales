/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.User;
import com.syntech.repository.UserRepository;
import com.syntech.util.MessageUtill;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rasmi
 */
@Named
@ViewScoped
public class UserController implements Serializable {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    private User user;
    private List<User> userList;

    @Inject
    private UserRepository userRepository;

    @Inject
    private MessageUtill messageUtill;

    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = User;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @PostConstruct
    public void init() {
        this.user = new User();
        this.userList = userRepository.findAll();
    }

    public void beforeCreate() {
        this.user = new User();
    }

    public void create() {
        try {
            String userpass = user.getPassword1();
            String userrepass = user.getRepassword();
            if (userpass.equals(userrepass)) {
                user.setPassword(hashPassword(user.getPassword1()));
                userRepository.create(user);
                this.userList = userRepository.findAll();
                messageUtill.showInfo("User Added Successfully", "Added User");
                logger.log(Level.INFO, " User Created Successfully!!!:");
            } else {
                logger.log(Level.SEVERE, " The password does not match.");
                messageUtill.showError("Password Doesn't Match", "Wrong password");

            }
        } catch (NullPointerException e) {
            messageUtill.showError("Failed to create User !!!", "User not Create !!!");
            logger.log(Level.SEVERE, " Failed to create User !!!", e);
        }

    }

    public void beforeEdit(User User) {
        this.user = userRepository.findById(User.getId());
    }

    public void edit() {
        try {
            userRepository.edit(this.user);
            this.userList = userRepository.findAll();
            messageUtill.showInfo("User Edited Successfully", "Edited User");
            logger.log(Level.INFO, " User Edited Successfully!!!:");
        } catch (Exception e) {
            logger.log(Level.SEVERE, " Failed to edit User !!!", e);
            messageUtill.showError("Message", "Failed to update User !!!");
        }
    }

    public void delete(User User) {
        try {
            userRepository.delete(User);
            userList = userRepository.findAll();
            messageUtill.showInfo("User Deleted Successfully", "Deleted User");
            logger.log(Level.INFO, " User Deleted Successfully !!!");
        } catch (Exception e) {
            logger.log(Level.SEVERE, " Failed to delete User !!!", e);
            messageUtill.showError("Failed to delete User !!!", "Failed to delete User !!!");
        }
    }

    private String hashPassword(String plainTextPassword) {
        String p = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
//        System.out.println(p + p.length());
        return p;
    }

}
