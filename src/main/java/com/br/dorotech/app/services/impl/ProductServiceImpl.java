package com.br.dorotech.app.services.impl;

import com.br.dorotech.app.exceptions.ResourceNotFoundException;
import com.br.dorotech.app.helper.ProductHelper;
import com.br.dorotech.app.models.dtos.ProductDTO;
import com.br.dorotech.app.models.entities.Product;
import com.br.dorotech.app.repositories.ProductRepository;
import com.br.dorotech.app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.br.dorotech.app.helper.ProductHelper.productsBuilder;
import static com.br.dorotech.app.helper.ProductHelper.productsDTOBuilder;
import static com.br.dorotech.app.helper.ProductHelper.productsUpdateBuilder;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO createNewProduct(ProductDTO productDTO) {
        productRepository.save(productsBuilder(productDTO));
        return productDTO;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(ProductHelper::productsDTOBuilder)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        return productsDTOBuilder(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        productRepository.delete(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        Product productUpdated = productsUpdateBuilder(product, productDTO);
        productRepository.save(productUpdated);
        return productDTO;
    }
}
