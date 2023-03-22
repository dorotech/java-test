package com.javatest.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.javatest.document.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
