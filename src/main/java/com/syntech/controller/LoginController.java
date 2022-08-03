/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.User;
import com.syntech.model.UserSession;
import com.syntech.repository.UserRepository;
import com.syntech.util.MessageUtill;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.event.ObservesAsync;
import javax.faces.context.FacesContext;
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
public class LoginController implements Serializable {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    private User user;

    @Inject
    private UserRepository userRepository;

    @Inject
    private MessageUtill messageUtill;

    @Inject
    private UserSession userSession;

    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = User;
    }

    @PostConstruct
    public void init() {
        this.user = new User();
    }

    public String userLogin() throws InterruptedException {
        User u = userRepository.findByUsername(user.getName());
        if (u == null || !BCrypt.checkpw(user.getPassword(), u.getPassword())) {
            logger.log(Level.SEVERE, " User doesn't exists");
            messageUtill.showError("User doesn't exists", "User not available");
            return "/login.xhtml?faces-redirect=true";
        }
        consumeEvent(user);
        userSession.setUser(u);
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        logger.log(Level.INFO, " Login Successfully!!");
        messageUtill.showError("Login Successfully!!", "Login Successfully!!");
        return "/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
    
    public void consumeEvent(@ObservesAsync User myEvent) throws InterruptedException {
        System.out.println("Email Event");
        logger.log(Level.INFO, "Email Event with TimeUnit");
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
