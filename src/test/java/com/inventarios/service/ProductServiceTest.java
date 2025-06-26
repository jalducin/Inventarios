
package com.inventarios.service;

import com.inventarios.model.Product;
import com.inventarios.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository repository;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    void shouldReturnAllProducts() {
        Product p = new Product();
        p.setId(1L);
        p.setName("Producto 1");
        p.setQuantity(5);
        p.setCategory("Categoria");

        when(repository.findAll()).thenReturn(Collections.singletonList(p));

        List<Product> result = service.findAll();
        assertEquals(1, result.size());
        assertEquals("Producto 1", result.get(0).getName());

        verify(repository, times(1)).findAll();
    }
}
