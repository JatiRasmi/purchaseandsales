/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntech.model.PurchaseOrder;
import com.syntech.repository.PurchaseOrderRepository;
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
@Path("/purchase")
public class PurchaseRestApi {

    @Inject
    PurchaseOrderRepository purchaseOrderRepository;

//    while creating through api we have to pass id of both purchase order and purchase order detail as null to avoid detached entity passed to persist error
    @POST
    @Path("create")
    public Response createPurchase(PurchaseOrder purchaseOrder) throws JsonProcessingException {
        purchaseOrderRepository.create(purchaseOrder);  
        return RestResponse.responseBuilder("true", "200", "Purchase Order Created Successfully", purchaseOrder);
    }

    @GET
    public Response getALLPurchase() throws JsonProcessingException {
        List<PurchaseOrder> po = (List<PurchaseOrder>) purchaseOrderRepository.eagerLoadAll();

        if (po == null) {
            return RestResponse.responseBuilder("false", "200", "Purchase Does not exists", null);
        }
        return RestResponse.responseBuilder("true", "200", "List of Purchase Order", po);
    }

    @GET
    @Path("{id}")
    public Response getPurchaseById(@PathParam("id") Long id) throws JsonProcessingException {
        PurchaseOrder po = purchaseOrderRepository.eagerload(id);
        if (po == null) {
            return RestResponse.responseBuilder("false", "200", "Purchase doesnot exists", null);
        }
        return RestResponse.responseBuilder("true", "200", "Purchase of given Id is", po);
    }

    @DELETE
    @Path("delete/{id}")
    public Response deletePurchase(@PathParam("id") Long id) throws JsonProcessingException {
        PurchaseOrder po = purchaseOrderRepository.eagerload(id);
        if (po == null) {
            return RestResponse.responseBuilder("false", "200", "Purchase doesnot exists", null);
        }
        purchaseOrderRepository.delete(po);
        return RestResponse.responseBuilder("true", "200", "Purchase Deleted Successfully", null);
    }

    @PUT
    @Path("edit/{id}")
    public Response editPurchase(@PathParam("id") Long id, PurchaseOrder purchaseOrder) throws JsonProcessingException {
        PurchaseOrder po = purchaseOrderRepository.eagerload(id);
        if (po == null) {
            return RestResponse.responseBuilder("false", "200", "Purchase doesnot exists", null);
        }
        purchaseOrderRepository.edit(purchaseOrder);
        return RestResponse.responseBuilder("true", "200", "Purchase Edited Successfully", purchaseOrder);
    }
}
