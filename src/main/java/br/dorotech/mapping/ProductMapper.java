package br.dorotech.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.dorotech.dto.request.ProductCreateRequest;
import br.dorotech.dto.request.ProductRequest;
import br.dorotech.dto.request.ProductUpdateRequest;
import br.dorotech.dto.response.ProductResponse;
import br.dorotech.entity.dynamodb.ProductEntity;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.UUID;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = IGNORE)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    ProductEntity toEntity(ProductCreateRequest request);

    // default String generateUUID(UUID id) {
    // return id.toString();
    // }

    ProductEntity toEntity(ProductUpdateRequest request, UUID id);

    @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(ProductRequest request);

    ProductResponse fromEntity(ProductEntity entity);
}
