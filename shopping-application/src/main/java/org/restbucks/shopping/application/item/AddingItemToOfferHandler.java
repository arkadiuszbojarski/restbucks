package org.restbucks.shopping.application.item;

import lombok.RequiredArgsConstructor;
import org.restbucks.shopping.application.common.CommandHandler;
import org.restbucks.shopping.domain.item.Item;
import org.restbucks.shopping.domain.item.ItemRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddingItemToOfferHandler implements CommandHandler<AddItemToOfferCommand> {

    private final ItemRepository repository;

    @Override
    public Class<AddItemToOfferCommand> supported() {
        return AddItemToOfferCommand.class;
    }

    @Override
    public void accept(final AddItemToOfferCommand command) {
        repository.save(Item.of(command.getItemID()));
    }
}
