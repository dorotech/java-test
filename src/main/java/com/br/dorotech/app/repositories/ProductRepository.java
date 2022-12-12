package com.br.dorotech.app.repositories;

import com.br.dorotech.app.models.entities.Product;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface ProductRepository extends DynamoDBCrudRepository<Product, String> {


}
