package com.javatest.api;


import com.javatest.document.Product;
import com.javatest.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
public class ProductRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ProductService service;

    @Autowired
    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        log.info("Getting all products.");
        return service.getAll();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable String id) {
        log.info("Getting product by Id.");
        Optional<Product> product = service.getById(id);
        if (product.isEmpty())
            return ResponseEntity.ok("No existe un producto con ese ID");
        else
            return ResponseEntity.ok(product.get());
    }

    @PostMapping("/products")
    public ResponseEntity<Object> create(@RequestBody Product product) {
        log.info("Creating new product");
        Product productNew = service.create(product);
        return ResponseEntity.ok(productNew);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable String id) {
        log.info("Deleting product");
        service.deleteById(id);
    }
}
