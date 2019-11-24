package org.restbucks.shopping.application.cart.item.add;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.restbucks.shopping.application.EventPublisher;
import org.restbucks.shopping.application.common.CommandHandler;
import org.restbucks.shopping.domain.cart.CartRepository;
import org.restbucks.shopping.domain.item.ItemRepository;
import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
@RequiredArgsConstructor
public class AddingItemToCustomerCartHandler implements CommandHandler<AddItemToCustomerCartCommand> {

    @NonNull private final EventPublisher publisher;
    @NonNull private final CartRepository cartRepository;
    @NonNull private final ItemRepository itemRepository;

    @Override
    public Class<AddItemToCustomerCartCommand> supported() {
        return AddItemToCustomerCartCommand.class;
    }

    @Override
    public void accept(final AddItemToCustomerCartCommand command) {
        final var cart = cartRepository.findByCustomerID(command.getCustomerID()).orElseThrow();
        final var item = itemRepository.findByItemID(command.getItemID()).orElseThrow();

        cart.getItems().add(item.getItemID());

        final var eventID = randomUUID();
        final var customerID = cart.getCustomerID();
        final var itemID = item.getItemID();
        final var quantity = command.getQuantity();

        final var event = ItemAddedToCustomerCartEvent.of(eventID, customerID, itemID, quantity);

        publisher.publish(event);
    }
}
