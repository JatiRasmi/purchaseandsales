/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntech.exception.CustomException;
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
        return RestResponse.responseBuilder("true", "200", " Unit Created ", unit);
    }

    @GET
    public Response getALLUnit() throws JsonProcessingException, CustomException {
        List<Unit> unit = unitRepository.findAll();
        if (unit == null || unit.isEmpty()) {
            throw new CustomException("Unit is null");
        } else {
            return RestResponse.responseBuilder("true", "200", "Unit List", unit);
        }
    }

    @GET
    @Path("{id}")
    public Response getUnitById(@PathParam("id") Long id) throws JsonProcessingException, CustomException {
        Unit unit = unitRepository.findById(id);
        if (unit == null) {
            throw new CustomException("Unit of id " + id +" is null");
        } else {
            return RestResponse.responseBuilder("true", "200", "Unit List By Id", unit);
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteUnit(@PathParam("id") Long id) throws JsonProcessingException, CustomException {
        Unit u = unitRepository.findById(id);
        if (u == null) {
            throw new CustomException("Unit of id " + id +" is null");
        } else {
            unitRepository.delete(u);
            return RestResponse.responseBuilder("true", "200", "Unit Deleted", null);
        }
    }

    @PUT
    @Path("edit/{id}")
    public Response editUnit(@PathParam("id") Long id, Unit unit) throws JsonProcessingException, CustomException {
        Unit u = unitRepository.findById(id);
        if (u == null) {
            throw new CustomException("Unit of id " + id +" is null");
        } else {
            unitRepository.edit(unit);
            return RestResponse.responseBuilder("true", "200", "Unit Edited", unit);
        }
    }
}
