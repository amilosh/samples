package pl.amilossh.spring_postgresql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.amilossh.spring_postgresql.model.Product;
import pl.amilossh.spring_postgresql.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.productRepository.getAll());
    }

    @Transactional
    @PostMapping("/{name}")
    public void createProduct(@PathVariable("name") String name) {
        var product = new Product(UUID.randomUUID(), name);
        productRepository.save(product);
    }
}
