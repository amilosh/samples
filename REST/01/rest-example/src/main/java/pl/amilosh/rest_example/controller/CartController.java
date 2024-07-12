package pl.amilosh.rest_example.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pl.amilosh.rest_example.dto.CartPayload;
import pl.amilosh.rest_example.model.Cart;
import pl.amilosh.rest_example.service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final MessageSource messageSource;

    public CartController(CartService cartService, MessageSource messageSource) {
        this.cartService = cartService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity<?> findCartEntries(@PathVariable UUID cartId) {
        try {
            return ResponseEntity.ok(cartService.findCartEntries(cartId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Cart>> findCarts() {
        var carts = new ArrayList<Cart>();
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(carts);
    }

    @PostMapping
    public ResponseEntity<?> createCart(
        @RequestBody CartPayload payload,
        UriComponentsBuilder uriComponentsBuilder,
        Locale locale) {
        var cart = new Cart();

        final var message = this.messageSource
            .getMessage("message.value", new Object[0], locale);

        return ResponseEntity.created(uriComponentsBuilder
                .path("/api/tasks/{taskId}")
                .build(Map.of("taskId", cart.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(cart);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cart> handleFindTask(@PathVariable("id") UUID id) {
        return ResponseEntity.of(cartService.findById(id));
    }
}
