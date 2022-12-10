package com.br.dorotech.app.services;

import com.br.dorotech.app.models.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createNewProduct(ProductDTO productDTO);

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long id);

    void deleteProduct(Long id);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

}
