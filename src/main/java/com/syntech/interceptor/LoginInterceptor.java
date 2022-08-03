/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.interceptor;

import com.syntech.api.RestResponse;
import com.syntech.controller.UserController;
import com.syntech.model.LoggedIn;
import com.syntech.model.User;
import com.syntech.repository.UserRepository;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rasmi
 */
@Interceptor
@LoggedIn
@Dependent
@Priority(Priorities.AUTHENTICATION)
public class LoginInterceptor {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Inject
    HttpServletRequest request;

    @Inject
    private UserRepository userRepository;

    @AroundInvoke
    public Object checkLoggedIn(InvocationContext context) throws Exception {

        String roleToken = request.getHeader("Authorization");
//        System.out.println("heaL: " + roleToken);

        if (roleToken == null) {
            return RestResponse.responseBuilder("false", "200", "Authorization Failed", null);
        }

        byte[] valueDecoded = Base64.getDecoder().decode(roleToken);
        String decoded = new String(Base64.getDecoder().decode(roleToken));
        //        System.out.println("Decoded value is " + new String(valueDecoded));

        String name = decoded.split(":")[0];
        String pass = decoded.split(":")[1];
//        System.out.println(pass); // plain password

        User user = userRepository.findByUsername(name);
        if (user == null) {
            return RestResponse.responseBuilder("false", "200", "User doesn't Exists", null);
        }
//                System.out.println(user.getPassword()); //hash password
        if (!BCrypt.checkpw(pass, user.getPassword())) {
            return RestResponse.responseBuilder("false", "500", "Incorrect Data", null);
        }
        logger.log(Level.INFO, " Authorization Succeed");
        return context.proceed();
    }
    
    
}
