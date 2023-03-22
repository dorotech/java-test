package com.javatest.service.impl;

import com.javatest.document.Product;
import com.javatest.repository.ProductRepository;
import com.javatest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public Boolean deleteById(String id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }
}
