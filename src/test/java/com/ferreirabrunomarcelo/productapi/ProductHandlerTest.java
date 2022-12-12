package com.ferreirabrunomarcelo.productapi;

import com.ferreirabrunomarcelo.productapi.configurations.RoutesConfiguration;
import com.ferreirabrunomarcelo.productapi.handlers.ProductHandler;
import com.ferreirabrunomarcelo.productapi.dtos.ProductDTO;
import com.ferreirabrunomarcelo.productapi.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@WebFluxTest
public class ProductHandlerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private ProductService productService;

    @MockBean
    private WebProperties.Resources resources;

    @BeforeEach
    void setUp() {

        ProductHandler productHandler = new ProductHandler(productService);

        RouterFunction<ServerResponse> routes = new RoutesConfiguration()
                .routes(productHandler);

        webTestClient = WebTestClient.bindToRouterFunction(routes)
                .build();
    }

    @Test
    public void shouldReturnHttpStatusOkWhenGetProductById() {

        Mockito.when(productService.getProduct("1"))
                .thenReturn(buildProduct());

        webTestClient
                .get()
                .uri("/product/" + "1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("name").isEqualTo("Product name")
                .jsonPath("description").isEqualTo("Product description")
                .jsonPath("price").isEqualTo(10.00)
                .jsonPath("amount").isEqualTo(100);
    }

    @Test
    public void shouldReturnHttpStatusNotFoundWhenGetProductById() {

        Mockito.when(productService.getProduct("1"))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        webTestClient
                .get()
                .uri("/product/" + "1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void shouldReturnAProductListWhenGetAllProducts() {
        Flux<ProductDTO> products = Flux.just(buildProduct().block(), buildProduct().block(), buildProduct().block());

        Mockito.when(productService.getAllProducts())
                .thenReturn(products);

        webTestClient
                .get()
                .uri("/product/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ProductDTO.class)
                .hasSize(3);
    }
    @Test
    public void shouldReturnHttpStatusNoContentWhenDeleteProduct() {
        Mockito.when(productService.deleteProduct("1"))
                .thenReturn(Mono.empty());

        webTestClient
                .delete()
                .uri("/product/" + "1")
                .exchange()
                .expectStatus()
                .isNoContent();
    }

    @Test
    public void shouldReturnHttpStatusNotFoundWhenDeleteProduct() {
        Mockito.when(productService.deleteProduct("1"))
                        .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        webTestClient
                .delete()
                .uri("/product/" + "1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    private Mono<ProductDTO> buildProduct() {
        return Mono.just(ProductDTO
                .builder()
                .name("Product name")
                .description("Product description")
                .price(BigDecimal.valueOf(10.00))
                .amount(100)
                .build());
    }
}
