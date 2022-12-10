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

}
