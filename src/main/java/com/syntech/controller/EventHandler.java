/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.EmailSender;
import com.syntech.model.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.ObservesAsync;

/**
 *
 * @author rasmi
 */
public class EventHandler implements Serializable {

    public static final Logger LOG = Logger.getLogger(EventHandler.class.getName());

    /**
     *
     * @param message
     */
    public void onMessage(@ObservesAsync @EmailSender User message) {
        LOG.log(Level.INFO, "observes event:{0}", message.getEmail());
    }
} 