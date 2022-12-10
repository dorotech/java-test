package com.br.dorotech.app.helper;

import com.br.dorotech.app.models.dtos.ProductDTO;
import com.br.dorotech.app.models.entities.Products;

public class ProductsHelper {

    public static ProductDTO productsDTOBuilder(Products products){
        return ProductDTO.builder()
                .amount(products.getAmount())
                .price(products.getPrice())
                .name(products.getName())
                .description(products.getDescription())
                .build();
    }

    public static Products productsBuilder(ProductDTO productDTO){
        return Products.builder()
                .amount(productDTO.getAmount())
                .price(productDTO.getPrice())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .build();
    }

    public static Products productsUpdateBuilder(Products products, ProductDTO productDTO){
        return Products.builder()
                .id(products.getId())
                .amount(productDTO.getAmount())
                .price(productDTO.getPrice())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .build();
    }

}
