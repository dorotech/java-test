package br.dorotech.repository.impl.dynamodb;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.dorotech.entity.dynamodb.ProductEntity;
import br.dorotech.repository.ProductRepository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository {

    @Inject
    DynamoDbClient client;

    DynamoDbTable<ProductEntity> enhanced;

    @PostConstruct
    void init() {
        this.enhanced = DynamoDbEnhancedClient.builder().dynamoDbClient(this.client)
                .build()
                .table("product", TableSchema.fromClass(ProductEntity.class));
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        enhanced.putItem(entity);
        return entity;
    }

    @Override
    public ProductEntity update(ProductEntity entity) {
        ProductEntity result = this.enhanced.updateItem(entity);
        return result;
    }

    @Override
    public Optional<ProductEntity> one(UUID id) {
        Key key = Key.builder().partitionValue(id.toString()).build();
        return this.enhanced.query(QueryConditional.keyEqualTo(key)).items().stream().findFirst();
    }

    @Override
    public List<ProductEntity> list(ProductEntity filter) {
        var result = this.enhanced.scan().items().stream().collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean remove(UUID id) {
        Key key = Key.builder().partitionValue(id.toString()).build();
        var item = this.enhanced.query(QueryConditional.keyEqualTo(key)).items().stream().findFirst();

        if (item.isPresent()) {
            var result = this.enhanced.deleteItem(item.get());
            return Optional.ofNullable(result).isPresent();
        }
        return false;
    }

}
