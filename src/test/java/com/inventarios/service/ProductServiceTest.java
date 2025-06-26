package com.inventarios.service;

import com.inventarios.model.Product;
import com.inventarios.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository repository;
    private SimpMessagingTemplate messaging;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        messaging  = mock(SimpMessagingTemplate.class);
        service     = new ProductService(repository, messaging);
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

    @Test
    void saveShouldNotifyWebSocketTopic() {
        Product p = new Product();
        p.setId(2L);
        p.setName("Test");
        when(repository.save(p)).thenReturn(p);
        when(repository.findAll()).thenReturn(Collections.singletonList(p));

        Product saved = service.save(p);

        assertEquals(p, saved);
        // Capturamos el payload que se envió
        ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
        verify(messaging, times(1))
          .convertAndSend(eq("/topic/products"), captor.capture());

        List<Product> sentPayload = (List<Product>) captor.getValue();
        assertEquals(1, sentPayload.size());
        assertEquals("Test", sentPayload.get(0).getName());
    }

    @Test
    void deleteShouldNotifyWebSocketTopic() {
        // Prepara el findAll tras eliminación
        when(repository.findAll()).thenReturn(Collections.emptyList());

        service.deleteById(5L);

        verify(repository, times(1)).deleteById(5L);
        verify(messaging, times(1))
          .convertAndSend(eq("/topic/products"), anyList());
    }
}
