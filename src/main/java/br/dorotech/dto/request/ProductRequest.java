package br.dorotech.dto.request;

import java.math.BigDecimal;

import org.jboss.resteasy.reactive.RestQuery;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ProductRequest {

    @RestQuery
    private String name;
    @RestQuery
    private String description;
    @RestQuery
    private BigDecimal price;
    @RestQuery
    private Long amount;

}
