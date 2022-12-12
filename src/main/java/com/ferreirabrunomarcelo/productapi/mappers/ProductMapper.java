package com.ferreirabrunomarcelo.productapi.mappers;


import com.ferreirabrunomarcelo.productapi.resources.ProductResource;
import com.ferreirabrunomarcelo.productapi.dtos.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(ProductResource productResource);
    ProductResource toProductResource(ProductDTO productDTO);
}
