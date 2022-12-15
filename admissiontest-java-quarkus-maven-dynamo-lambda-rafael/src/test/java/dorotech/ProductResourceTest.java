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
    public void testProductGetEndpoint404() {
        given()
          .when().get("/product")
          .then()
             .statusCode(404);
    }

    @Test
    @Order(2)
    public void testProductPostEndpoint() throws JsonProcessingException {
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
    public void testProductGetEndpoint200() throws JsonProcessingException {
        Product bodyRequest = new Product();
        bodyRequest.setName("Fechadura eletrônica YALE");
        bodyRequest.setDescription("Fechadura eletrônica biométrica da marca YALE, código YMF40A , compatível com alexa");
        bodyRequest.setPrice(599.99d);
        bodyRequest.setAmount(1);
        ObjectMapper om = new ObjectMapper();
        given()
          .when().get("/product")
          .then()
             .statusCode(200)
             .body(is(om.writeValueAsString(List.of(bodyRequest))));
    }

    @Test
    @Order(4)
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
    @Order(5)
    public void testProductGetEndpoint200_with2Products() throws JsonProcessingException {
        Product bodyRequest = new Product();
        bodyRequest.setName("Fechadura eletrônica YALE");
        bodyRequest.setDescription("Fechadura eletrônica biométrica da marca YALE, código YMF40A , compatível com alexa");
        bodyRequest.setPrice(599.99d);
        bodyRequest.setAmount(1);
        Product bodyRequest2 = new Product();
        bodyRequest2.setName("ELAY - Fechadura eletrônica");
        bodyRequest2.setDescription("Fechadura eletrônica biométrica da marca YALE, código YMF40A , compatível com alexa, alterada");
        bodyRequest2.setPrice(599.99d);
        bodyRequest2.setAmount(1);

        ObjectMapper om = new ObjectMapper();
        given()
          .when().get("/product")
          .then()
             .statusCode(200)
             .body(is(om.writeValueAsString(List.of(bodyRequest, bodyRequest2))));
    }

    @Test
    @Order(6)
    public void testProductDeleteEndpoint200_with2Products() throws JsonProcessingException {
        given()
          .when().delete("/product?name=ELAY - Fechadura eletrônica")
          .then()
             .statusCode(204);
    }

    @Test
    @Order(7)
    public void testProductPutEndpoint_nocontent_204() throws JsonProcessingException {
        Product bodyRequest = new Product();
        bodyRequest.setName("Fechadura eletrônica YALE");
        bodyRequest.setDescription("Fechadura eletrônica biométrica da marca YALE, código YMF40A , compatível com alexa, alterada para desconto");
        bodyRequest.setPrice(499.99d);
        bodyRequest.setAmount(2);
        ObjectMapper om = new ObjectMapper();
        given()
          .when()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body(om.writeValueAsString(bodyRequest))
            .put("/product?name=Fechadura eletrônica YALE")
          .then()
             .statusCode(204);
    }

    @Test
    @Order(8)
    public void testProductDeleteEndpoint200_with1Products() throws JsonProcessingException {
        Product bodyRequest = new Product();
        bodyRequest.setName("Fechadura eletrônica YALE");
        bodyRequest.setDescription("Fechadura eletrônica biométrica da marca YALE, código YMF40A , compatível com alexa, alterada para desconto");
        bodyRequest.setPrice(499.99d);
        bodyRequest.setAmount(2);
        ObjectMapper om = new ObjectMapper();
        given()
          .when().get("/product")
          .then()
             .statusCode(200)
             .body(is(om.writeValueAsString(List.of(bodyRequest))));
    }
}