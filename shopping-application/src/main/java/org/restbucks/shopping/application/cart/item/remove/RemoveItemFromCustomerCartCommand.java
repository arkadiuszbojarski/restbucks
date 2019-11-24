package org.restbucks.shopping.application.cart.item.remove;

import lombok.Value;
import org.restbucks.shopping.application.common.message.Command;

import java.util.UUID;

@Value(staticConstructor = "of")
public class RemoveItemFromCustomerCartCommand implements Command {
    private final UUID customerID;
    private final UUID itemID;
    private final String type = "customer.cart.item.remove";
}
