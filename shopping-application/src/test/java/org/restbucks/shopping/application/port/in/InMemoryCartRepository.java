package org.restbucks.shopping.application.port.in;

import org.restbucks.shopping.domain.cart.Cart;
import org.restbucks.shopping.domain.cart.CartRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

class InMemoryCartRepository implements CartRepository {

    private final Map<UUID, Cart> carts = new HashMap<>();

    @Override
    public void save(final Cart cart) {
        requireNonNull(cart);
        carts.put(cart.getCustomerID(), cart);
    }

    @Override
    public Optional<Cart> findByCustomerID(final UUID customerID) {
        requireNonNull(customerID);
        return ofNullable(carts.get(customerID));
    }
}
