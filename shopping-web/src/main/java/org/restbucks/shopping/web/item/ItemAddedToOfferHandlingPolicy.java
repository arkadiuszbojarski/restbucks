package org.restbucks.shopping.web.item;

import lombok.RequiredArgsConstructor;
import org.restbucks.shopping.application.ShoppingFacade;
import org.restbucks.shopping.application.item.AddItemToOfferCommand;
import org.restbucks.shopping.web.EventHandlingPolicy;
import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
@RequiredArgsConstructor
public class ItemAddedToOfferHandlingPolicy implements EventHandlingPolicy<ItemAddedToOfferEvent> {

    private final ShoppingFacade facade;

    @Override
    public void accept(final ItemAddedToOfferEvent event) {
        final var itemID = event.getItemID();
        final var price = event.getPrice();
        final var currency = event.getCurrency();

        facade.accept(AddItemToOfferCommand.of(randomUUID(), itemID, price, currency));
    }
}
