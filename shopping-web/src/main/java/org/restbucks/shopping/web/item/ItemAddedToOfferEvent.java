package org.restbucks.shopping.web.item;

import lombok.Value;
import org.restbucks.shopping.application.common.message.Event;

import java.math.BigDecimal;
import java.util.UUID;

@Value(staticConstructor = "of")
public class ItemAddedToOfferEvent implements Event {
    private final UUID eventID;
    private final UUID itemID;
    private final String itemCode;
    private final BigDecimal price;
    private final String currency;
    private final String type = "item.add.to.offer";
}
