package br.dorotech.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ProductResponse {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long amount;
}
