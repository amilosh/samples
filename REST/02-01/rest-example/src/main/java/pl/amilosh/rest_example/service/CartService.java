package pl.amilosh.rest_example.service;

import pl.amilosh.rest_example.common.Result;
import pl.amilosh.rest_example.model.CartEntry;
import pl.amilosh.rest_example.operation.FindCartEntries;

import java.util.List;
import java.util.UUID;

public interface CartService {

    Result<List<CartEntry>> findCartEntriesWayOne(UUID cartId);

    // instead of List<> and throws we return 'result' object - 'Result ot Exception' pattern
    FindCartEntries.Result findCartEntriesWayTwo(FindCartEntries query);
}
