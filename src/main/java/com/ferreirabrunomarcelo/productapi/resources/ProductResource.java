package com.ferreirabrunomarcelo.productapi.resources;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResource {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer amount;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductResource productResource)) {
            return false;
        }
        return this.getId().equals(productResource.getId());
    }
}
