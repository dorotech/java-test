package dorotech;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dorotech.domain.Product;
import dorotech.service.ProductService;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    private ProductService service;

    @GET
    @Path("/")
    public Response allProducts(@QueryParam("name") String name) {
        if(name != null && !name.trim().isEmpty()){
            return Response.ok(List.of(service.get(name))).build();
        }
        List<Product> listaProdutos = service.findAll();
        if(listaProdutos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
        return Response.ok(listaProdutos).build();
    }

    @POST
    @Path("/")
    public Response newProduct(@Valid List<Product> products) {
        service.add(products);
        return Response.status(Response.Status.CREATED).entity(products).build();
    }

    @PUT
    @Path("/")
    public Response updateProduct(@Valid Product newProduct, @QueryParam("name") @Valid @NotBlank(message = "O nome do produto não pode estar vazio ou somente com espaço") String productName) {
        
        List<Product> products = service.findAll();
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getName().equals(productName)){
                service.update(newProduct);
                return Response.noContent().build();
            }
        }
        service.add(List.of(newProduct));
        return Response.status(Response.Status.CREATED).entity(newProduct).build();
    }

    @DELETE
    @Path("/")
    public Response deleteProduct(@QueryParam("name") @NotBlank(message = "O nome do produto não pode estar vazio ou somente com espaço") String productName) {
        service.delete(productName);
        return Response.noContent().build();
    }

}