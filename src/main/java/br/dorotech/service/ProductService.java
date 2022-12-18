package br.dorotech.service;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import br.dorotech.dto.request.ProductCreateRequest;
import br.dorotech.dto.request.ProductRequest;
import br.dorotech.dto.request.ProductUpdateRequest;
import br.dorotech.dto.response.DeleteResponse;
import br.dorotech.dto.response.ProductResponse;

@ApplicationScoped
public interface ProductService {

    ProductResponse save(ProductCreateRequest request);

    ProductResponse update(ProductUpdateRequest request, UUID id);

    ProductResponse one(UUID id);

    List<ProductResponse> list(ProductRequest filter);

    DeleteResponse remove(UUID id);
}
