package org.restbucks.shopping.domain.cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    void save(Cart cart);
    Optional<Cart> findByCustomerID(UUID customerID);
}
