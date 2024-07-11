package pl.amilosh.rest_example.operation;

import pl.amilosh.rest_example.model.CartEntry;

import java.util.List;
import java.util.UUID;

// describes operation with entries parameters
public class FindCartEntries {

    private final UUID cartId;

    public FindCartEntries(UUID cartId) {
        this.cartId = cartId;
    }

    public static FindCartEntries of(UUID cartId) {
        return new FindCartEntries(cartId);
    }

    public UUID getCartId() {
        return cartId;
    }

    // need to be documented which result should be returns in which situation
    // describes all valid results
    public interface Result {

        // 'Visitor' pattern
        <T> T process(Processor<T> processor);

        // on prod List<CartEntry> entries have to be not modified in order to further not to change elements
        static Result success(List<CartEntry> entries) {
            return Success.of(entries);
        }

        static Result cartNotFound() {
            return CartNotFound.INSTANCE;
        }

        class Success implements Result {
            private final List<CartEntry> entries;

            public Success(List<CartEntry> entries) {
                this.entries = entries;
            }

            public static Success of(List<CartEntry> entries) {
                return new Success(entries);
            }

            public List<CartEntry> getEntries() {
                return entries;
            }

            // We don't define Type of object and do something according to type; we allow object do something itself.
            // Inside this method we can do anything with result.
            @Override
            public <T> T process(Processor<T> processor) {
                return processor.processSuccess(this);
            }
        }

        enum CartNotFound implements Result {
            INSTANCE;

            @Override
            public <T> T process(Processor<T> processor) {
                return processor.processCartNotFound(this);
            }
        }

        // visitor pattern
        // transform result to our view
        interface Processor<T> {
            T processSuccess(Success result);

            T processCartNotFound(CartNotFound result);
        }
    }
}
