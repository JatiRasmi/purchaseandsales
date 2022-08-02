///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.interceptor;
//
//import javax.inject.Inject;
//import javax.interceptor.AroundInvoke;
//import javax.interceptor.InvocationContext;
//import javax.servlet.http.HttpServletRequest;
//
///**
// *
// * @author rasmi
// */
//public class PortDetectionInterceptor {
//
//    @Inject
//    private HttpServletRequest httpRequest;
//
//    @AroundInvoke
//    public Object detectPort(InvocationContext ctx) throws Exception {
//        try {
//            String restEndpoint = ctx.getTarget().getClass().getName();
//            int restPort = httpRequest.getLocalPort();
//
//            if (restEndpoint.contains("CustomerRestApi") && restPort != 8081) {
//                throw new RuntimeException("Please access CustomerRestApi only via port 8081");
//            }
//
//            if (restEndpoint.contains("SupplierRestApi") && restPort != 8080) {
//                throw new RuntimeException("Please access supplierRestApi only via port 8080");
//            }
//
//            return ctx.proceed();
//        } catch (Exception e) {
//            return String.format("{ \"error\" : \"%s\" }", e.getMessage());
//        }
//    }
//}
