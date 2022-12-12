package com.ferreirabrunomarcelo.productapi.services;

import com.ferreirabrunomarcelo.productapi.dtos.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDTO> getProduct(String id);
    Flux<ProductDTO> getAllProducts();
    Mono<ProductDTO> createProduct(ProductDTO productDTO);
    Mono<ProductDTO> updateProduct(ProductDTO productDTO, String id);
    Mono<Void> deleteProduct(String id);
}
