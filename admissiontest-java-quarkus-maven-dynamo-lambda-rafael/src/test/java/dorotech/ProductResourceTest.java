package dorotech;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dorotech.domain.Product;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import javax.ws.rs.core.MediaType;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductResourceTest {

    @Test
    @Order(1)
    public void testProductGetEndpoint_notfoun_404() {
        given()
          .when().get("/product")
          .then()
             .statusCode(404);
    }

    @Test
    @Order(2)
    public void testProductPostEndpoint_create_201() throws JsonProcessingException {
        Product bodyRequest = new Product();
        bodyRequest.setName("Fechadura eletrônica YALE");
        bodyRequest.setDescription("Fechadura eletrônica biométrica da marca YALE, código YMF40A , compatível com alexa");
        bodyRequest.setPrice(599.99d);
        bodyRequest.setAmount(1);
        ObjectMapper om = new ObjectMapper();
        
        given()
          .header("Content-Type", MediaType.APPLICATION_JSON)
          .body(om.writeValueAsString(List.of(bodyRequest)))
          .when()
            .post("/product")
          .then()
             .statusCode(201)
             .body(is(om.writeValueAsString(List.of(bodyRequest))));
    }

    @Test
    @Order(3)
    public void testProductPutEndpoint_create_201() throws JsonProcessingException {
        Product bodyRequest = new Product();
        bodyRequest.setName("ELAY - Fechadura eletrônica");
        bodyRequest.setDescription("Fechadura eletrônica biométrica da marca YALE, código YMF40A , compatível com alexa, alterada");
        bodyRequest.setPrice(599.99d);
        bodyRequest.setAmount(1);
        ObjectMapper om = new ObjectMapper();
        given()
          .when()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body(om.writeValueAsString(bodyRequest))
            .put("/product?name=a")
          .then()
             .statusCode(201)
             .body(is(om.writeValueAsString(bodyRequest)));
    }

    @Test
    @Order(4)
    public void testProductDeleteEndpoint_nocontent_204() throws JsonProcessingException {
        given()
          .when().delete("/product?name=ELAY - Fechadura eletrônica")
          .then()
             .statusCode(204);
    }

}