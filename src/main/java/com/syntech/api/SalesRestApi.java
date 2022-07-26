/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntech.exception.CustomException;
import com.syntech.model.SalesOrder;
import com.syntech.repository.SalesOrderRepository;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/sales")
public class SalesRestApi {

    @Inject
    SalesOrderRepository salesOrderRepository;

    @POST
    @Path("create")
    public Response createSales(SalesOrder salesOrder) throws JsonProcessingException {
        salesOrderRepository.create(salesOrder);
        return RestResponse.responseBuilder("true", "200", "Sales Order Created Successfully", salesOrder);
    }

    @GET
    public Response getALLSales() throws JsonProcessingException, CustomException {
        List<SalesOrder> so = salesOrderRepository.eagerLoadAll();
        if (so == null) {
            throw new CustomException("Sales Order is null");
        }
        return RestResponse.responseBuilder("true", "200", "List of Sales Order", so);
    }

    @GET
    @Path("{id}")
    public Response getSalesById(@PathParam("id") Long id) throws JsonProcessingException, CustomException {
        SalesOrder so = salesOrderRepository.eagerload(id);
        if (so == null) {
            throw new CustomException("Sales Order of id " + id +" is null");
        }
        return RestResponse.responseBuilder("true", "200", "Sales of given Id is", so);
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteSales(@PathParam("id") Long id) throws JsonProcessingException, CustomException {
        SalesOrder so = salesOrderRepository.eagerload(id);
        if (so == null) {
            throw new CustomException("Sales Order of id " + id +" is null");
        }
        salesOrderRepository.delete(so);
        return RestResponse.responseBuilder("true", "200", "Sales Deleted Successfully", null);
    }

    @PUT
    @Path("edit/{id}")
    public Response editSales(@PathParam("id") Long id, SalesOrder salesOrder) throws JsonProcessingException, CustomException {
        SalesOrder so = salesOrderRepository.eagerload(id);
        if (so == null) {
            throw new CustomException("Sales Order of id " + id +" is null");
        }
        salesOrderRepository.edit(salesOrder);
        return RestResponse.responseBuilder("true", "200", "Sales Edited Successfully", salesOrder);
    }
}
