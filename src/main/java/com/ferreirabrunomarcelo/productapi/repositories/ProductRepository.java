package com.ferreirabrunomarcelo.productapi.repositories;

import com.ferreirabrunomarcelo.productapi.resources.ProductResource;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface ProductRepository extends ReactiveMongoRepository<ProductResource, String> {
}
