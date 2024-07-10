package pl.amilosh.rest_example.service;

import org.springframework.stereotype.Service;
import pl.amilosh.rest_example.model.Cart;
import pl.amilosh.rest_example.model.CartEntry;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public abstract class DefaultCartService implements CartService {

    abstract Cart findCartInternal(UUID cartId);

    abstract List<CartEntry> findCartEntriesInternal(UUID cartId);

    @Override
    public List<CartEntry> findCartEntries(UUID cartId) {
        if (findCartInternal(cartId) != null) {
            return findCartEntriesInternal(cartId);
        }

        throw new NoSuchElementException();
    }
}
