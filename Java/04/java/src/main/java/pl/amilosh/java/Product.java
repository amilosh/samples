package pl.amilosh.java;

import java.io.Serializable;
import java.util.UUID;

public record Product(UUID id, String name) implements Serializable {
    /* Constructor without parameters
    public Product { // created before fields initialization
        id.toString();
        name.equals("");
        // this.id; - not allowed
    }
     */

    /*
    // Override constructor
    public Product(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
     */

    // add parameter to constructor - need invoke default constructor
    public Product(UUID id, String name, int version) {
        this(id, name);
    }
}
