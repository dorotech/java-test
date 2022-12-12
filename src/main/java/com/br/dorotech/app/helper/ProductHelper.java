package com.br.dorotech.app.helper;

import com.br.dorotech.app.models.dtos.ProductDTO;
import com.br.dorotech.app.models.entities.Product;

public class ProductHelper {

    public static ProductDTO productsDTOBuilder(Product product){
        return ProductDTO.builder()
                .amount(product.getAmount())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

    public static Product productsBuilder(ProductDTO productDTO){
        return Product.builder()
                .amount(productDTO.getAmount())
                .price(productDTO.getPrice())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .build();
    }

    public static Product productsUpdateBuilder(Product product, ProductDTO productDTO){
        return Product.builder()
                .id(product.getId())
                .amount(productDTO.getAmount())
                .price(productDTO.getPrice())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .build();
    }

}
