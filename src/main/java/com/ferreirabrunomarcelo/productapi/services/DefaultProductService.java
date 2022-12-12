package com.ferreirabrunomarcelo.productapi.services;

import com.ferreirabrunomarcelo.productapi.mappers.ProductMapper;
import com.ferreirabrunomarcelo.productapi.repositories.ProductRepository;
import com.ferreirabrunomarcelo.productapi.dtos.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public Mono<ProductDTO> getProduct(String id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Flux<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .map(productMapper::toProductDTO);
    }

    @Override
    public Mono<ProductDTO> createProduct(ProductDTO productDTO) {
        return productRepository.save(productMapper.toProductResource(productDTO))
                .map(productMapper::toProductDTO);
    }

    @Override
    public Mono<ProductDTO> updateProduct(ProductDTO productDTO, String id) {
        return productRepository.findById(id)
                .flatMap(p -> {
                    p.setName(productDTO.getName());
                    p.setDescription(productDTO.getDescription());
                    p.setPrice(productDTO.getPrice());
                    p.setAmount(productDTO.getAmount());
                    return productRepository.save(p)
                            .map(productMapper::toProductDTO);
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
       return productRepository.deleteById(id);
    }
}
