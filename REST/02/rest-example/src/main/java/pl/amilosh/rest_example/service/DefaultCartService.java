package pl.amilosh.rest_example.service;

import org.springframework.stereotype.Service;
import pl.amilosh.rest_example.common.Result;
import pl.amilosh.rest_example.model.Cart;
import pl.amilosh.rest_example.model.CartEntry;
import pl.amilosh.rest_example.result.FindCartEntriesResult;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public abstract class DefaultCartService implements CartService {

    abstract Cart findCartInternal(UUID cartId);

    abstract List<CartEntry> findCartEntriesInternal(UUID cartId);

    @Override
    public Result<List<CartEntry>> findCartEntriesWayOne(UUID cartId) {
        if (findCartInternal(cartId) != null) {
            return Result.success(findCartEntriesInternal(cartId));
        }

        return Result.failure(new NoSuchElementException());
    }

    @Override
    public FindCartEntriesResult findCartEntriesWayTwo(UUID cartId) {
        if (findCartInternal(cartId) != null) {
            return FindCartEntriesResult.success(findCartEntriesInternal(cartId));
        }

        return FindCartEntriesResult.cartNotFound();
    }
}
