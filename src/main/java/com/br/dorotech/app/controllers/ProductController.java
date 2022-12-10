package com.br.dorotech.app.controllers;

import com.br.dorotech.app.models.dtos.ProductDTO;
import com.br.dorotech.app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllFarms() {
        final List<ProductDTO> productDTOS = productService.findAllProducts();
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }



}
