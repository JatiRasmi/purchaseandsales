/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntech.model.Customer;
import com.syntech.repository.CustomerRepository;
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
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerRestApi {
    
    @Inject
    private CustomerRepository customerRepository;
    
    @POST
    @Path("create")
    public Response createCustomer(Customer customer) throws JsonProcessingException{
        customerRepository.create(customer);
        return RestResponse.responseBuilder("true", "200", "Customer Created Successfully", customer);
    }
    
    @GET
    public Response getAllCustomer() throws JsonProcessingException{
        List<Customer> customer = customerRepository.findAll();
        if(customer==null){
            return RestResponse.responseBuilder("false", "200", "No Customer Exists", null);
        }
        return RestResponse.responseBuilder("true", "200", "List of Customer", customer);
    }
    
    @GET
    @Path("{id}")
    public Response getCustomerById(@PathParam("id") Long id) throws JsonProcessingException{
        Customer c = customerRepository.findById(id);
        if(c==null){
            return RestResponse.responseBuilder("false", "200", "Customer with Id"+id+"doesn't exist", c);
        }
        return RestResponse.responseBuilder("true", "200", "List of customer of Id "+id+" is:", c);
    }
    
    @DELETE
    @Path("delete/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) throws JsonProcessingException{
        Customer c = customerRepository.findById(id);
        if(c==null){
            return RestResponse.responseBuilder("false", "200", "Customer doesn't exists", null);
        }
        customerRepository.delete(c);
        return RestResponse.responseBuilder("true", "200", "Customer Deleted Successfully", c);
    }
    
    @PUT
    @Path("edit/{id}")
    public Response editCustomer(@PathParam("id") Long id, Customer customer) throws JsonProcessingException{
        Customer c = customerRepository.findById(id);
        if(c==null){
            return RestResponse.responseBuilder("false", "200", "Customer doesn't exists", null);
        }
        customerRepository.edit(customer);
        return RestResponse.responseBuilder("true", "200", "Customer Edited Successfully", customer);    
    }
}
