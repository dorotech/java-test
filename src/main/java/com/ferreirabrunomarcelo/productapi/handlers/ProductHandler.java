package com.ferreirabrunomarcelo.productapi.handlers;

import com.ferreirabrunomarcelo.productapi.dtos.ProductDTO;
import com.ferreirabrunomarcelo.productapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {
    private final ProductService productService;

    public Mono<ServerResponse> getProduct(ServerRequest request) {
        String productId = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getProduct(productId), ProductDTO.class);
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAllProducts(), ProductDTO.class);
    }

    public Mono<ServerResponse> createProduct(ServerRequest request) {
        return request
                .bodyToMono(ProductDTO.class)
                .flatMap(p -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .body(productService.createProduct(p), ProductDTO.class));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        String productId = request.pathVariable("id");
        return request
                .bodyToMono(ProductDTO.class)
                .flatMap(p -> ServerResponse
                        .ok()
                        .body(productService.updateProduct(p, productId), ProductDTO.class));

    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        String productId = request.pathVariable("id");
        return ServerResponse.noContent().build(productService.deleteProduct(productId));
    }
}
