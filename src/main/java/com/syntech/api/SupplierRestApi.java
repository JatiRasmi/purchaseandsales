/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntech.exception.CustomException;
import com.syntech.model.LoggedIn;
import com.syntech.model.Supplier;
import com.syntech.repository.SupplierRepository;
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
@Path("/supplier")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierRestApi {

    @Inject
    private SupplierRepository supplierRepository;

    @LoggedIn
    @POST
    @Path("create")
    public Response createSupplier(Supplier supplier) throws JsonProcessingException {
        supplierRepository.create(supplier);
        return RestResponse.responseBuilder("true", "200", "Supplier created successully", supplier);
    }

    @GET
    public Response getAllSupplier() throws JsonProcessingException, CustomException {
        List<Supplier> supplier = supplierRepository.findAll();
        if (supplier == null) {
            throw new CustomException("Supplier is null");
        }
        return RestResponse.responseBuilder("true", "200", "Available Suppliers", supplier);
    }

    @GET
    @Path("{id}")
    public Response getSupplierById(@PathParam("id") Long id) throws JsonProcessingException, CustomException {
        Supplier supplier = supplierRepository.findById(id);
        if (supplier == null) {
            throw new CustomException("Supplier of id " + id + " is null");
        }
        return RestResponse.responseBuilder("true", "200", "List of Supplier by Id", supplier);
    }

    @LoggedIn
    @DELETE
    @Path("delete/{id}")
    public Response deleteSupplier(@PathParam("id") Long id) throws JsonProcessingException, CustomException {
        Supplier supplier = supplierRepository.findById(id);
        if (supplier == null) {
            throw new CustomException("Supplier of id " + id + " is null");
        }
        supplierRepository.delete(supplier);
        return RestResponse.responseBuilder("true", "200", "Supplier deleted Successfully", supplier);
    }

    @LoggedIn
    @PUT
    @Path("edit/{id}")
    public Response editSupplier(@PathParam("id") Long id, Supplier supplier) throws JsonProcessingException, CustomException {
        Supplier s = supplierRepository.findById(id);
        if (s == null) {
            throw new CustomException("Supplier of id " + id + " is null");
        }
        supplierRepository.edit(supplier);
        return RestResponse.responseBuilder("true", "200", "Supplier Edited Successfully", supplier);
    }
}
