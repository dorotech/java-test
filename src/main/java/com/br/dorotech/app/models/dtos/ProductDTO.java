package com.br.dorotech.app.models.dtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProductDTO {

    private String name;

    private String description;

    private Double price;

    private Double amount;

}
