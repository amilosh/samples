package pl.amilosh.rest_example.operation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public enum FindCartEntriesResultToResponseEntityMapper implements FindCartEntries.Result.Processor<ResponseEntity<?>> {
    INSTANCE;

    @Override
    public ResponseEntity<?> processSuccess(FindCartEntries.Result.Success result) {
        return ResponseEntity.ok(result.getEntries());
    }

    @Override
    public ResponseEntity<?> processCartNotFound(FindCartEntries.Result.CartNotFound result) {
        return ResponseEntity.notFound().build();
    }
}
