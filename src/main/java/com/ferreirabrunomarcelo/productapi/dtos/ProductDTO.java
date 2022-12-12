package com.ferreirabrunomarcelo.productapi.dtos;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer amount;
}
