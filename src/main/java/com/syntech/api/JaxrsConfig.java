/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author rasmi
 */
@ApplicationPath("/api")
public class JaxrsConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
        classes.add(UserRestApi.class);
        classes.add(CustomerRestApi.class);
        classes.add(SupplierRestApi.class);
        classes.add(ReportRestApi.class);
        classes.add(UnitRestApi.class);
        classes.add(ProductRestApi.class);
        return classes;
    }
}
