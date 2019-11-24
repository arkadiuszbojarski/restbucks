package org.restbucks.shopping.application.cart.item.remove;

import lombok.RequiredArgsConstructor;
import org.restbucks.shopping.application.EventPublisher;
import org.restbucks.shopping.domain.cart.CartRepository;
import org.restbucks.shopping.application.common.CommandHandler;
import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
@RequiredArgsConstructor
public class RemovingItemFromCustomerCartHandler implements CommandHandler<RemoveItemFromCustomerCartCommand> {

    private final EventPublisher publisher;
    private final CartRepository repository;

    @Override
    public Class<RemoveItemFromCustomerCartCommand> supported() {
        return RemoveItemFromCustomerCartCommand.class;
    }

    @Override
    public void accept(final RemoveItemFromCustomerCartCommand command) {
        final var cart = repository.findByCustomerID(command.getCustomerID()).orElseThrow();

        if (cart.getItems().contains(command.getItemID())) {
            final var eventId = randomUUID();
            final var customerID = command.getCustomerID();
            final var itemID = command.getItemID();

            final var event = ItemRemovedFromCustomerCartEvent.of(eventId, customerID, itemID);

            publisher.publish(event);
        }
    }
}
