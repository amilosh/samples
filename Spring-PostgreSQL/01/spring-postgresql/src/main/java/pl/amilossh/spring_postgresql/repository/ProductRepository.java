package pl.amilossh.spring_postgresql.repository;

import pl.amilossh.spring_postgresql.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();

    void save(Product product);

    Optional<Product> getById(Integer id);
}
