package org.restbucks.shopping.application.cart.item.add;

import lombok.Value;
import org.restbucks.shopping.application.common.message.Event;

import java.util.UUID;

@Value(staticConstructor = "of")
public class ItemAddedToCustomerCartEvent implements Event {
    private final UUID eventID;
    private final UUID customerID;
    private final UUID itemID;
    private final int quantity;
    private final String type = "customer.cart.item.added";
}
