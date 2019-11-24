package org.restbucks.shopping.domain.item;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository {
    void save(Item item);

    Optional<Item> findByItemID(UUID itemID);
}
