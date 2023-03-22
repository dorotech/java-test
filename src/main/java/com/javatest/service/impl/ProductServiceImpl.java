package com.javatest.service.impl;

import com.javatest.document.Product;
import com.javatest.repository.ProductRepository;
import com.javatest.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(getClass());

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
    public Optional<Product> getById(String id) {
        Optional<Product> unitOptional = repository.findById(id);
        if (unitOptional.isEmpty())
            return Optional.empty();
        return unitOptional;
    }

    @Override
    public Boolean deleteById(String id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }

    @Override
    public Product create(Product product) {
        Product product1 = new Product();
        try {
            product1 = repository.save(product);

        }catch (RuntimeException e){
            log.error(e.getMessage());
        }
        return product1;
    }
}
