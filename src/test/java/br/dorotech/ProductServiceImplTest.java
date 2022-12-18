package br.dorotech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import br.dorotech.domain.model.WebCustomExeption;
import br.dorotech.dto.request.ProductCreateRequest;
import br.dorotech.entity.dynamodb.ProductEntity;
import br.dorotech.repository.ProductRepository;
import br.dorotech.service.ProductService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

@QuarkusTest
public class ProductServiceImplTest {

    @Inject
    private ProductService subject;

    @InjectMock
    private ProductRepository repository;

    @Test
    public void productServiceShouldSaveProductRequest() {

        ProductEntity entity = new ProductEntity();
        entity.setId(UUID.randomUUID());
        entity.setAmount(BigDecimal.ONE.longValue());
        entity.setPrice(BigDecimal.TEN);
        entity.setName("Teste");
        entity.setDescription("Description Test");

        ProductCreateRequest request = new ProductCreateRequest();
        request.setAmount(BigDecimal.ONE.longValue());
        request.setPrice(BigDecimal.TEN);
        request.setName("Teste");
        request.setDescription("Description Test");

        when(this.repository.save(any())).thenReturn(entity);

        var result = this.subject.save(request);
        assertNotNull(result);
    }

    @Test()
    public void productServiceShouldThrowsWebCustomExeption() {

        var result = assertThrows(WebCustomExeption.class, () -> this.subject.save(null));
        assertEquals(result.getMessage(), "Invalid product");
        assertEquals(result.getOperation(), "save");
    }

    @Test
    public void productServiceOneShouldReturnProductResponse() {
        UUID one = UUID.randomUUID();

        ProductEntity entity = new ProductEntity();
        entity.setId(one);
        entity.setAmount(BigDecimal.ONE.longValue());
        entity.setPrice(BigDecimal.TEN);
        entity.setName("Teste");
        entity.setDescription("Description Test");

        when(this.repository.one(eq(one))).thenReturn(Optional.of(entity));

        var result = this.subject.one(one);
        assertNotNull(result);
        assertThat(result, hasProperty("id", is(one)));
        assertThat(result, hasProperty("amount", is(entity.getAmount())));
        assertThat(result, hasProperty("price", is(entity.getPrice())));
        assertThat(result, hasProperty("name", containsString(entity.getName())));
        assertThat(result, hasProperty("description", containsString(entity.getDescription())));
    }

    @Test
    public void productServiceOneShouldThrowException() {
        UUID one = UUID.randomUUID();
        var result = assertThrows(WebCustomExeption.class, () -> this.subject.one(eq(one)));

        assertEquals(result.getMessage(), "Product not found");
        assertEquals(result.getOperation(), "one");
    }

}
