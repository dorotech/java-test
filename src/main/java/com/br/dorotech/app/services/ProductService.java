package com.br.dorotech.app.services;

import com.br.dorotech.app.models.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO startProductCreation(ProductDTO productDTO);

    void finishProductCreation(ProductDTO productDTO);

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(String id);

    void deleteProduct(String id);

    ProductDTO updateProduct(String id, ProductDTO productDTO);

}
