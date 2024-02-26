package main;

import java.util.function.Consumer;

public class User {

    private Address address;

    public User() {
    }

    public User(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    void address(Consumer<Address> customizer) {
        customizer.accept(this.address);
    }
}
