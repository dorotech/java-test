package com.br.dorotech.app.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private String name;

    private String description;

    private Double price;

    private Double amount;

}
