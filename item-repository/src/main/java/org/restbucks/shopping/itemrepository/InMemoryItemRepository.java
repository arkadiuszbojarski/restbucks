package org.restbucks.shopping.itemrepository;

import org.restbucks.shopping.domain.item.Item;
import org.restbucks.shopping.domain.item.ItemRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

@Component
public class InMemoryItemRepository implements ItemRepository {

    private final Map<UUID, Item> items = new ConcurrentHashMap<>();

    @Override
    public void save(final Item item) {
        requireNonNull(item);
        items.put(item.getItemID(), item);
    }

    @Override
    public Optional<Item> findByItemID(final UUID itemID) {
        return ofNullable(items.get(itemID));
    }
}
