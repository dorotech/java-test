package br.dorotech.dto.request;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ProductCreateRequest {

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 100)
    private String description;

    @NotNull
    private BigDecimal price;

    @Min(value = 1)
    @NotNull
    private Long amount;
}
