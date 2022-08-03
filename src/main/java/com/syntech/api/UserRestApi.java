/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntech.exception.CustomException;
import com.syntech.model.LoggedIn;
import com.syntech.model.User;
import com.syntech.repository.UserRepository;
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
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestApi {

    @Inject
    private UserRepository userRepository;

    @POST
    @Path("create")
    @LoggedIn
    public Response createUser(User user) throws JsonProcessingException {
        userRepository.create(user);
        return RestResponse.responseBuilder("true", "200", " User Created ", user);
    }

    @GET
    @LoggedIn
    public Response getALLUser() throws JsonProcessingException, CustomException {
        List<User> user = userRepository.findAll();
        if (user == null || user.isEmpty()) {
            throw new CustomException("User is null");
        } else {
            return RestResponse.responseBuilder("true", "200", "User List", user);
        }
    }

    @GET
    @LoggedIn
    @Path("{id}")
    public Response getUserById(@PathParam("id") Long id) throws JsonProcessingException, CustomException {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new CustomException("User of id " + id + " is null");
        } else {
            return RestResponse.responseBuilder("true", "200", "User List By Id", user);
        }
    }

    @LoggedIn
    @DELETE
    @Path("delete/{id}")
    public Response deleteUser(@PathParam("id") Long id) throws JsonProcessingException, CustomException {
        User u = userRepository.findById(id);
        if (u == null) {
            throw new CustomException("User of id " + id + " is null");
        } else {
            userRepository.delete(u);
            return RestResponse.responseBuilder("true", "200", "User Deleted", null);
        }
    }

    @LoggedIn
    @PUT
    @Path("edit/{id}")
    public Response editUser(@PathParam("id") Long id, User user) throws JsonProcessingException, CustomException {
        User u = userRepository.findById(id);
        if (u == null) {
            throw new CustomException("User of id " + id + " is null");
        } else {
            userRepository.edit(user);
            return RestResponse.responseBuilder("true", "200", "User Edited", user);
        }
    }
}
