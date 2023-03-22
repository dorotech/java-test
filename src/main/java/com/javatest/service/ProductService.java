package com.javatest.service;

import com.javatest.document.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<Product> getAll();

    Optional<Product> getById(String id);

    Boolean deleteById(String id);

    Product create(Product product);

}
