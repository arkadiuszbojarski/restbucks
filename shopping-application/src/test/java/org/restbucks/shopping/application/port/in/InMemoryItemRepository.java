package org.restbucks.shopping.application.port.in;

import org.restbucks.shopping.domain.item.Item;
import org.restbucks.shopping.domain.item.ItemRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class InMemoryItemRepository implements ItemRepository {

    private final Map<UUID, Item> items = new ConcurrentHashMap<>();

    @Override
    public void save(final Item item) {
        requireNonNull(item);
        items.put(item.getItemID(), item);
    }

    @Override
    public Optional<Item> findByItemID(final UUID itemID) {
        requireNonNull(itemID);
        return ofNullable(items.get(itemID));
    }
}
