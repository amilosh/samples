package pl.amilosh.java;

import java.util.UUID;

public class AddProductToCartOld {

    private UUID cartId;
    private UUID productId;
    private int amount;

    public AddProductToCartOld(UUID cartId, UUID productId, int amount) {
        this.cartId = cartId;
        this.productId = productId;
        this.amount = amount;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public interface Result {
        enum CartNotFound implements Result {
            INSTANCE;
        }

        enum ProductNotFound implements Result {
            INSTANCE;
        }
    }
}
