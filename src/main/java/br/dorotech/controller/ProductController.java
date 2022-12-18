package br.dorotech.controller;

import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.dorotech.dto.request.ProductCreateRequest;
import br.dorotech.dto.request.ProductRequest;
import br.dorotech.dto.request.ProductUpdateRequest;
import br.dorotech.service.ProductService;

@Path("/api/product")
public class ProductController {

    @Inject
    ProductService service;

    @Inject
    Validator validator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(ProductRequest request) {
        var result = this.service.list(request);
        return Response.ok(result).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response one(UUID id) {
        var result = this.service.one(id);
        return Response.ok(result).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid ProductCreateRequest request) {
        var result = this.service.save(request);
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(UUID id, ProductUpdateRequest request) {
        var result = this.service.update(request, id);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(UUID id) {
        var result = this.service.remove(id);
        return Response.ok(result).build();
    }
}
