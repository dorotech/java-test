package dorotech;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dorotech.domain.Product;
import dorotech.service.ProductService;
import io.quarkus.test.Mock;

@ApplicationScoped
@Mock
public class ProductSyncServiceMock implements ProductService{

    @Override
    public List<Product> findAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Product> add(List<Product> products) {
        return products;
    }

    @Override
    public Product get(String productName) {
        Product p = new Product();
        p.setName("Fechadura eletrônica YALE");
        p.setDescription("Fechadura eletrônica biométrica da marca YALE, código YMF40A , compatível com alexa");
        p.setPrice(599.99d);
        p.setAmount(1);
        return p;
    }

    @Override
    public void update(Product product) {
    }

    @Override
    public void delete(String productName) {
    }
    
}
