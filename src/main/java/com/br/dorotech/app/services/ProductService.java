package com.br.dorotech.app.services;

import com.br.dorotech.app.models.dtos.ProductDTO;

public interface ProductService {

    ProductDTO createNewProduct(ProductDTO productDTO);

    ProductDTO findAllProducts();

    ProductDTO findProductById(Long id);

    void deleteProduct(Long id);

    void updateProduct(Long id);

}
