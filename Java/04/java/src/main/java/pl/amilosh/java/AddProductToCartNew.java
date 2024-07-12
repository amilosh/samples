package pl.amilosh.java;

import java.util.UUID;

public record AddProductToCartNew(UUID cartId, UUID productId, int amount) {

    public sealed interface Result {
        enum CartNotFound implements Result {
            INSTANCE;
        }

        enum ProductNotFound implements Result {
            INSTANCE;
        }
    }
}
