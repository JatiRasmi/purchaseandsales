/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntech.api.RestResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author rasmi
 */
@Provider
public class ExceptionHandler implements javax.ws.rs.ext.ExceptionMapper<CustomException> {

    @Override
    public Response toResponse(CustomException e) {
        try {
            return RestResponse.responseBuilder("false", "500", e.getMessage(), null);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ExceptionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    return Response.status(Status.BAD_REQUEST).entity("Internal server error").build();  
    }
}
