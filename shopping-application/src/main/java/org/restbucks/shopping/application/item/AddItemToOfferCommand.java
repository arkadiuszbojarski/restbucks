package org.restbucks.shopping.application.item;

import lombok.Value;
import org.restbucks.shopping.application.common.message.Command;

import java.math.BigDecimal;
import java.util.UUID;

@Value(staticConstructor = "of")
public class AddItemToOfferCommand implements Command {
    private final UUID commandID;
    private final UUID itemID;
    private final BigDecimal price;
    private final String currency;
    private final String type = "item.add.to.offer";
}
