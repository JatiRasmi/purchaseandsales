/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.interceptor;

import com.syntech.api.RestResponse;
import com.syntech.model.LoggedIn;
import com.syntech.model.UserSession;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.Priorities;

/**
 *
 * @author rasmi
 */
@Interceptor
@LoggedIn
@Dependent
@Priority(Priorities.AUTHORIZATION)

public class LoginInterceptor {

    @Inject
    private UserSession userSession;

    @AroundInvoke
    public Object checkLoggedIn(InvocationContext context) throws Exception {

        boolean isLoggedIn = true;
        
//        boolean isLoggedIn = userSession.getUser() != null;
        if (isLoggedIn) {
            return context.proceed();
        } else {
            return RestResponse.responseBuilder("false", "304", "User cannot access the Resource", null);
        }
    }
}
