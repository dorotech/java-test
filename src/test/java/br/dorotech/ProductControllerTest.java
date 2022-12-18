package br.dorotech;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.dorotech.dto.request.ProductCreateRequest;
import br.dorotech.dto.request.ProductRequest;
import br.dorotech.dto.response.ProductResponse;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest {

    @Test
    @Order(1)
    public void whenPostProduct_thenProductShouldReturnedOk() throws JsonProcessingException {
        ProductCreateRequest request = new ProductCreateRequest();
        request.setAmount(BigDecimal.ONE.longValue());
        request.setPrice(BigDecimal.TEN);
        request.setName("Teste " + new Random().ints(Integer.MIN_VALUE, Integer.MAX_VALUE).findFirst().getAsInt());
        request.setDescription(
                "Description Test " + +new Random().ints(Integer.MIN_VALUE, Integer.MAX_VALUE).findFirst().getAsInt());

        String json = new ObjectMapper().writeValueAsString(request);

        var response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(json)
                .when()
                .request("POST", "/api/product")
                .then()
                .extract()
                .response();

        assertEquals(200, response.statusCode());
        assertNotNull(response.jsonPath().getUUID("id"));
        assertEquals(request.getAmount(), response.jsonPath().getLong("amount"));
        assertEquals(request.getDescription(), response.jsonPath().getString("description"));
        assertEquals(request.getName(), response.jsonPath().getString("name"));
        assertEquals(request.getPrice(), response.jsonPath().getObject("price", BigDecimal.class));
    }

    @Test
    @Order(2)
    public void whenPostProduct_thenProductShouldReturnedBadRequest() throws JsonProcessingException {
        ProductCreateRequest request = new ProductCreateRequest();
        String json = new ObjectMapper().writeValueAsString(request);

        var response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(json)
                .when()
                .request("POST", "/api/product")
                .then()
                .extract()
                .response();

        assertEquals(400, response.statusCode());
    }

    @Test
    @Order(3)
    public void whenGetProducts_thenProductsShouldReturnedProductList() throws JsonProcessingException {
        ProductRequest request = new ProductRequest();
        String json = new ObjectMapper().writeValueAsString(request);

        var response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(json)
                .when()
                .request("GET", "/api/product")
                .then()
                .extract()
                .response();

        var result = response.getBody().asString();
        List<ProductResponse> list = Arrays.asList(new ObjectMapper().readValue(result, ProductResponse[].class));

        assertEquals(200, response.statusCode());
        assertTrue(list.size() > 0);
    }

}
