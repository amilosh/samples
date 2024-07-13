package pl.amiloh.spring_tests.service;

import org.springframework.stereotype.Service;
import pl.amiloh.spring_tests.model.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAll() {
        return null;
    }
}
