package pl.amilosh.rest_example.result;

import pl.amilosh.rest_example.model.CartEntry;

import java.util.List;

public interface FindCartEntriesResult {

    // 'Visitor' pattern
    <T> T process(Processor<T> processor);

    // on prod List<CartEntry> entries have to be not modified in order to further not to change elements
    static FindCartEntriesResult success(List<CartEntry> entries) {
        return Success.of(entries);
    }

    static FindCartEntriesResult cartNotFound() {
        return CartNotFound.INSTANCE;
    }

    class Success implements FindCartEntriesResult {
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

    enum CartNotFound implements FindCartEntriesResult {
        INSTANCE;

        @Override
        public <T> T process(Processor<T> processor) {
            return processor.processCartNotFound(this);
        }
    }

    // visitor pattern
    interface Processor<T> {
        T processSuccess(Success result);
        T processCartNotFound(CartNotFound result);
    }
}
