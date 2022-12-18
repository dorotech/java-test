package br.dorotech.entity.dynamodb;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Setter
@NoArgsConstructor
@EqualsAndHashCode
@DynamoDbBean
public class ProductEntity {

    private UUID id;

    @DynamoDbPartitionKey()
    @DynamoDbAttribute(value = "id")
    public UUID getId() {
        return id;
    }

    private String name;

    @DynamoDbAttribute(value = "name")
    public String getName() {
        return name;
    }

    private String description;

    @DynamoDbAttribute(value = "description")
    public String getDescription() {
        return description;
    }

    private BigDecimal price;

    @DynamoDbSortKey()
    @DynamoDbAttribute(value = "price")
    public BigDecimal getPrice() {
        return price;
    }

    private Long amount;

    @DynamoDbAttribute(value = "amount")
    public Long getAmount() {
        return amount;
    }
}
