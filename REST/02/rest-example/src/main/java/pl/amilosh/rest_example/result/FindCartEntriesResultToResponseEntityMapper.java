package pl.amilosh.rest_example.result;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public enum FindCartEntriesResultToResponseEntityMapper implements FindCartEntriesResult.Processor<ResponseEntity<?>> {
    INSTANCE;

    @Override
    public ResponseEntity<?> processSuccess(FindCartEntriesResult.Success result) {
        return ResponseEntity.ok(result.getEntries());
    }

    @Override
    public ResponseEntity<?> processCartNotFound(FindCartEntriesResult.CartNotFound result) {
        return ResponseEntity.notFound().build();
    }
}
