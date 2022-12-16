package dorotech.service;

import java.util.List;

import dorotech.domain.Product;

public interface ProductService {
    
    public List<Product> findAll();
    
    public List<Product> add(List<Product> products);

    public Product get(String productName);

    public void update(Product product);
    
    public void delete(String productName);
}
