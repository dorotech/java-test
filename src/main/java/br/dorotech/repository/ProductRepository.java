package br.dorotech.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.dorotech.entity.dynamodb.ProductEntity;

public interface ProductRepository {

    ProductEntity save(ProductEntity entity);

    ProductEntity update(ProductEntity entity);

    Optional<ProductEntity> one(UUID id);

    List<ProductEntity> list(ProductEntity filter);

    boolean remove(UUID id);

}
