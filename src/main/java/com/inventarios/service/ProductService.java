package com.inventarios.service;

import com.inventarios.model.Product;
import com.inventarios.repository.ProductRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final SimpMessagingTemplate messaging;

    public ProductService(ProductRepository repository,
                          SimpMessagingTemplate messaging) {
        this.repository = repository;
        this.messaging = messaging;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product save(Product product) {
        Product saved = repository.save(product);
        // Notificar a todos los clientes conectados
        messaging.convertAndSend("/topic/products", findAll());
        return saved;
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
        // Actualizar listado en tiempo real
        messaging.convertAndSend("/topic/products", findAll());
    }

    public List<Product> filterByCategory(String category) {
        return repository.findByCategoryContainingIgnoreCase(category);
    }
}
