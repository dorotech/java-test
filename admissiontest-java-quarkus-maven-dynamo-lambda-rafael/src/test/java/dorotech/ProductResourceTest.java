package dorotech;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/product")
          .then()
             .statusCode(404)
             .body(is(""));
    }

}