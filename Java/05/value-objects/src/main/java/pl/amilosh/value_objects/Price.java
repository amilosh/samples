package pl.amilosh.value_objects;

import java.util.Currency;

// value object class
public final class Price {

    private final double price;
    private final Currency currency;

    public Price(double price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    // get / hashCode equals / toString
}
