package org.restbucks.shopping.application.cart.item.add;

import lombok.NonNull;
import lombok.Value;
import org.restbucks.shopping.application.common.message.Command;

import java.util.UUID;

@Value(staticConstructor = "of")
public class AddItemToCustomerCartCommand implements Command {
    @NonNull private final UUID commandID;
    @NonNull private final UUID customerID;
    @NonNull private final UUID itemID;
    @NonNull private final int quantity;
    private final String type = "customer.cart.item.add";
}
