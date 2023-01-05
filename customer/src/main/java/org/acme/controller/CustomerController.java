package org.acme.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.dto.CustomerForm;
import org.acme.dto.CustomerResponse;
import org.acme.service.CustomerService;

@Path("/customers")
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @GET
    public CustomerResponse findById(Long id) {
        return customerService.findById(id);
    }

    @POST
    public Response create(CustomerForm valueForm) {
        customerService.create(valueForm);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response edit(Long id, CustomerForm valueForm) {
        customerService.edit(id, valueForm);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        customerService.delete(id);
        return Response.ok().build();
    }
}