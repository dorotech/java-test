package dorotech;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

@Path("/product")
public class ProductResource {

    private List<Product> products = new ArrayList<>();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allProducts(@QueryParam("name") String name) {
        if(this.products.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
        if(name != null && !name.trim().isEmpty()){
            return Response.ok(
                this.products.stream()
                    .filter(product -> product.getName().equals(name))
                    .collect(Collectors.toList())
                )
            .build();
        }
        return Response.ok(this.products).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct(List<Product> products) {
        this.products.addAll(products);
        return Response.status(Response.Status.CREATED).entity(products).build();
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(Product newProduct, @QueryParam("name") String productName) {
        
        // sei que dava pra utilizar streams do java
        // porém como tem mais de uma opção de retorno pra PUT (https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT#responses), 
        // eu precisaria validar se o objeto foi encontrado ou não dentro do stream, 
        // e eu não consigo redefinir um valor de uma variavel boolean ou int dentro de uma função .map do stream por exemplo
        // por isso achei mais facil fazer o for comum
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getName().equals(productName)){
                products.set(i, newProduct);
                // pela convenção http, quando alteramos um produto que existe, ele devolve 204-noContent
                return Response.noContent().build();
            }
        }
        // pela convenção http, quando tentamos alterar um produto que não existe, e o serviço de persistencia devolve ok, ele adiciona e devolve 201-created
        this.products.add(newProduct);
        return Response.status(Response.Status.CREATED).entity(newProduct).build();
    }

    @DELETE
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@QueryParam("name") String productName) {
        this.products.removeIf(product -> product.getName().equals(productName));
        return Response.noContent().build();
    }

}