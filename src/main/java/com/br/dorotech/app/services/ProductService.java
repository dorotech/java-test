package com.br.dorotech.app.services;

import com.br.dorotech.app.models.dtos.ProductDTO;
import com.br.dorotech.app.models.entities.Product;

import java.util.List;

public interface ProductService {

    ProductDTO startProductCreation(ProductDTO productDTO);

    void finishProductCreation(ProductDTO productDTO);

    List<Product> findAllProducts();

    ProductDTO findProductById(String id);

    void deleteProduct(String id);

    ProductDTO updateProduct(String id, ProductDTO productDTO);

}
