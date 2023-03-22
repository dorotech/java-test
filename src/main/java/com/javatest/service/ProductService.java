package com.javatest.service;

import com.javatest.document.Product;

import java.util.List;


public interface ProductService {

    List<Product> getAll();

    Product getById(String id);

    Boolean deleteById(String id);

    Product create(Product product);

}
