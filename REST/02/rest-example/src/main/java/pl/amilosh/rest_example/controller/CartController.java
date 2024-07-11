package pl.amilosh.rest_example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.amilosh.rest_example.result.FindCartEntriesResult;
import pl.amilosh.rest_example.service.CartService;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private FindCartEntriesResult.Processor<ResponseEntity<?>> processor;

    @GetMapping
    public ResponseEntity<?> findCartEntriesWayOne(@PathVariable UUID cartId) {
        var result = cartService.findCartEntriesWayOne(cartId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getResult());
        } else if (result.getException() instanceof NoSuchElementException) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> findCartEntriesWayTwo(@PathVariable UUID cartId) {
        var result = cartService.findCartEntriesWayTwo(cartId);
        if (result instanceof FindCartEntriesResult.Success) {
            return ResponseEntity.ok(((FindCartEntriesResult.Success) result).getEntries());
        } else if (result instanceof FindCartEntriesResult.CartNotFound) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> findCartEntriesWayThree(@PathVariable UUID cartId) {
        return cartService.findCartEntriesWayTwo(cartId).process(processor);
    }
}
