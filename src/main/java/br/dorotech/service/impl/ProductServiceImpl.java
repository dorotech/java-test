package br.dorotech.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.http.HttpStatus;

import br.dorotech.domain.model.WebCustomExeption;
import br.dorotech.dto.request.ProductCreateRequest;
import br.dorotech.dto.request.ProductRequest;
import br.dorotech.dto.request.ProductUpdateRequest;
import br.dorotech.dto.response.DeleteResponse;
import br.dorotech.dto.response.ProductResponse;
import br.dorotech.entity.dynamodb.ProductEntity;
import br.dorotech.mapping.ProductMapper;
import br.dorotech.repository.ProductRepository;
import br.dorotech.service.ProductService;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository repository;

    @Inject
    private ProductMapper productMapper;

    @Override
    public ProductResponse save(ProductCreateRequest request) {
        ProductEntity entity = this.productMapper.toEntity(request);
        Optional.ofNullable(entity).orElseThrow(
                () -> new WebCustomExeption(HttpStatus.SC_BAD_REQUEST, "Invalid product", "save"));

        ProductEntity result = this.repository.save(entity);
        ProductResponse response = this.productMapper.fromEntity(result);
        return response;
    }

    @Override
    public ProductResponse update(ProductUpdateRequest request, UUID id) {
        Optional<ProductEntity> found = this.repository.one(id);
        found.orElseThrow(() -> new WebCustomExeption(HttpStatus.SC_NOT_FOUND, "Product not found", "update"));

        ProductEntity entity = this.productMapper.toEntity(request, id);
        ProductEntity result = this.repository.update(entity);
        ProductResponse response = this.productMapper.fromEntity(result);
        return response;
    }

    @Override
    public ProductResponse one(UUID id) {
        Optional<ProductEntity> found = this.repository.one(id);
        ProductEntity entity = found
                .orElseThrow(() -> new WebCustomExeption(HttpStatus.SC_NOT_FOUND, "Product not found", "one"));

        ProductResponse response = this.productMapper.fromEntity(entity);
        return response;
    }

    @Override
    public List<ProductResponse> list(ProductRequest filter) {
        ProductEntity entity = this.productMapper.toEntity(filter);

        List<ProductEntity> result = this.repository.list(entity);
        List<ProductResponse> response = result.stream().map(this.productMapper::fromEntity).toList();
        return response;
    }

    @Override
    public DeleteResponse remove(UUID id) {
        Optional<ProductEntity> found = this.repository.one(id);
        found.orElseThrow(() -> new WebCustomExeption(HttpStatus.SC_NOT_FOUND, "Product not found", "delete"));
        boolean result = this.repository.remove(id);
        return new DeleteResponse(result);
    }

}
