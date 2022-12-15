package dorotech.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Product {

    @NotBlank(message = "O nome do produto não pode estar vazio ou somente com espaço")
    private String name;
    @NotBlank(message = "A descrição do produto não pode estar vazio ou somente com espaço")
    private String description;
    @NotNull(message = "O preço do produto não pode ser nulo")
    private Double price;
    @NotNull(message = "A quantidade do produto não pode ser nulo")
    private Integer amount;

    public Product(){
        super();
    }

    public Product(@NotBlank(message = "O nome do produto não pode estar vazio ou somente com espaço") String name,
            @NotBlank(message = "A descrição do produto não pode estar vazio ou somente com espaço") String description,
            @NotNull(message = "O preço do produto não pode ser nulo") Double price,
            @NotNull(message = "A quantidade do produto não pode ser nulo") Integer amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        return true;
    }

    
}
