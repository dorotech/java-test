package dorotech.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import dorotech.domain.Product;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class ProductSyncService implements ProductService{
    
    private final DynamoDbClient dynamoDbClient;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<Product> productTable;

    public ProductSyncService() {
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .build();
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        productTable = 
            dynamoDbEnhancedClient.table("product",
            TableSchema.fromClass(Product.class));
    }

    @Override
    public List<Product> findAll() {
        return productTable.scan().items().stream().collect(Collectors.toList());
    }

    @Override
    public List<Product> add(List<Product> products) {
        
        products.parallelStream().forEach(product -> {
            productTable.putItem(product);
        });
        return findAll();
    }

    @Override
    public Product get(String productName) {
        Key partitionKey = Key.builder().partitionValue(productName).build();
        return productTable.getItem(partitionKey);
    }

    @Override
    public void update(Product product){
        productTable.updateItem(product);
    }
    
    @Override
    public void delete(String productName){
        Key partitionKey = Key.builder().partitionValue(productName).build();
        productTable.deleteItem(partitionKey);
    }
}
