package pl.amilosh.rest_example.service;

import pl.amilosh.rest_example.model.Cart;
import pl.amilosh.rest_example.model.CartEntry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {

    List<CartEntry> findCartEntries(UUID cartId);

    Optional<Cart> findById(UUID cartId);
}
