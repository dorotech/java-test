package com.br.dorotech.app.controllers;

import com.br.dorotech.app.models.dtos.ProductDTO;
import com.br.dorotech.app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        final List<ProductDTO> productDTOS = productService.findAllProducts();
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/createProduct")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        final ProductDTO createProductDTO = productService.createNewProduct(productDTO);
        return new ResponseEntity<>(createProductDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getProductById/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        final ProductDTO productDTO = productService.findProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        final String msg = "Product Id " + id + " deleted!";
        productService.deleteProduct(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping(value = "/updateProduct/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        final ProductDTO productUpdateDTO = productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(productUpdateDTO, HttpStatus.OK);
    }

}
