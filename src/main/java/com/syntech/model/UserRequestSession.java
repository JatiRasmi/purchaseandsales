///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.model;
//
//import com.syntech.repository.UserRepository;
//import java.io.Serializable;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
///**
// *
// * @author rasmi
// */
//@Named
//@RequestScoped
//public class UserRequestSession implements Serializable {
//
//    private UserSession userSession;
//    @Inject
//    private UserRepository userRepository;
//
//    public User getUser() {
//        return userSession.isLoggedIn()
//                ? userRepository.findByUsername(userSession.getUser().getName())
//                : null;
//    }
//}
