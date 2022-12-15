package dorotech.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Product {

    @NotBlank(message = "O nome do produto não pode estar vazio ou somente com espaço")
    private String name;
    @NotBlank(message = "A descrição do produto não pode estar vazio ou somente com espaço")
    private String description;
    @NotNull(message = "O preço do produto não pode ser nulo")
    private Double price;
    @NotNull(message = "A quantidade do produto não pode ser nulo")
    private Integer amount;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
