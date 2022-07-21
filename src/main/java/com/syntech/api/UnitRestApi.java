/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.Unit;
import com.syntech.repository.UnitRepository;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author rasmi
 */
@Path("/unit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnitRestApi {

    @Inject
    private UnitRepository unitRepository;

    @POST
    @Path("create")
    public Response createUnit(Unit unit) throws JsonProcessingException {
            unitRepository.create(unit);
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(unit);
            return RestResponse.responseBuilder("true", "200", " Unit Created ", str);
    }

    @GET
    public Response getALLUnit() throws JsonProcessingException {
        List<Unit> unit = unitRepository.findAll();
        if (unit == null || unit.isEmpty()) {
            return RestResponse.responseBuilder("Failed!!!", "204", "Unit Doesnot Exist",null);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(unit);
            return RestResponse.responseBuilder("true", "200", "Unit List", str);
        }
    }

    @GET
    @Path("{id}")
    public Response getUnitById(@PathParam("id") Long id) throws JsonProcessingException {
        Unit unit = unitRepository.findById(id);
        if (unit == null) {
            return RestResponse.responseBuilder("Failed!!!", "204", "Unit of Entered Id Doesnot Exist",null);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(unit);
            return RestResponse.responseBuilder("true", "200", "Unit List By Id", str);
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteUnit(@PathParam("id") Long id) throws JsonProcessingException {
        Unit u = unitRepository.findById(id);
        if (u == null) {
            return RestResponse.responseBuilder("false", "304", "Unit Not Deleted",null);
        } else {
            unitRepository.delete(u);
            return RestResponse.responseBuilder("true", "200", "Unit Deleted", null);
        }
    }

    @PUT
    @Path("edit/{id}")
    public Response editUnit(@PathParam("id") Long id, Unit unit) throws JsonProcessingException {
        Unit u = unitRepository.findById(id);
        if (u == null) {
            return RestResponse.responseBuilder("false", "304", "Failed to Edit Unit ","null");
        } else {
            unitRepository.edit(unit);
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(unit);
            return RestResponse.responseBuilder("true", "200", "Unit Edited", str);
        }
    }
}
