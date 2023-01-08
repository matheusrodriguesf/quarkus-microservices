package org.acme.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.dto.ProductForm;
import org.acme.dto.ProductResponse;
import org.acme.service.ProductService;

import lombok.RequiredArgsConstructor;

@Path("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductResponse> all() {
        return productService.all();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductResponse findById(Long id) {
        return productService.findById(id);
    }

    @POST
    public Response create(ProductForm form) {
        productService.create(form);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(Long id, ProductForm form) {
        productService.update(id, form);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        productService.delete(id);
        return Response.ok().build();
    }
}
