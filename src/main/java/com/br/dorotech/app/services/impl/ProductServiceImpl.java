package com.br.dorotech.app.services.impl;

import com.br.dorotech.app.exceptions.ResourceNotFoundException;
import com.br.dorotech.app.helper.ProductHelper;
import com.br.dorotech.app.models.dtos.ProductDTO;
import com.br.dorotech.app.models.entities.Product;
import com.br.dorotech.app.repositories.ProductRepository;
import com.br.dorotech.app.services.ProductService;
import com.br.dorotech.app.sqs.ProductMessageProducer;
import com.br.dorotech.app.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.br.dorotech.app.helper.ProductHelper.productsBuilder;
import static com.br.dorotech.app.helper.ProductHelper.productsDTOBuilder;
import static com.br.dorotech.app.helper.ProductHelper.productsUpdateBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMessageProducer productMessageProducer;

    @Value("${amazon.queue.product-creation}")
    private String queueName;

    @Override
    public ProductDTO startProductCreation(ProductDTO productDTO) {
        productMessageProducer.sentToQueue(queueName, JsonUtil.writeValueAsString(productDTO));
        log.info("***** PRODUCT CREATION DATA SENT TO QUEUE:  " + queueName + ", PRODUCT NAME: " + productDTO.getName()
                + ", PRODUCT DESCRIPTION: " + productDTO.getDescription());
        return productDTO;
    }

    @Override
    public void finishProductCreation(ProductDTO productDTO){
        productRepository.save(productsBuilder(productDTO));
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        return productList.stream()
                .map(ProductHelper::productsDTOBuilder)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        return productsDTOBuilder(product);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        productRepository.delete(product);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        Product productUpdated = productsUpdateBuilder(product, productDTO);
        productRepository.save(productUpdated);
        return productDTO;
    }

}
