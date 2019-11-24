package org.restbucks.shopping.cartrepository;

import org.restbucks.shopping.domain.cart.Cart;
import org.restbucks.shopping.domain.cart.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@Repository
public class InMemoryCartRepository implements CartRepository {

    private final Map<UUID, Cart> carts = new ConcurrentHashMap<>();

    @Override
    public void save(final Cart cart) {
        requireNonNull(cart);
        carts.put(cart.getCustomerID(), cart);
    }

    @Override
    public Optional<Cart> findByCustomerID(final UUID customerID) {
        requireNonNull(customerID);
        return Optional.ofNullable(carts.get(customerID));
    }
}
