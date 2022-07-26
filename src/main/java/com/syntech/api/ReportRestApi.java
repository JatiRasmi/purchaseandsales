/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntech.adaptar.ReportGeneration;
import com.syntech.model.DayBook;
import java.util.Date;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author rasmi
 */
@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReportRestApi {
 
    @Inject
    private ReportGeneration reportGeneration;
    @GET
    @Path("{date}")
    public Response getReport(@PathParam("date") Date date) throws JsonProcessingException {
        DayBook db = reportGeneration.preparedaybook(date);
        return RestResponse.responseBuilder("true", "200", "Transaction Report Generated Successfully",db );
    }
}
