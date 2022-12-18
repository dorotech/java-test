package br.dorotech.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ProductUpdateRequest {

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 100)
    private String description;

    private BigDecimal price;

    @Min(value = 1)
    private Long amount;

}
