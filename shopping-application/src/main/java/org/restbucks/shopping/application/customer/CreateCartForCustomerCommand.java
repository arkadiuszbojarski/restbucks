package org.restbucks.shopping.application.customer;

import lombok.Value;
import org.restbucks.shopping.application.common.message.Command;

import java.util.UUID;

@Value(staticConstructor = "of")
public class CreateCartForCustomerCommand implements Command {
    private final UUID commandID;
    private final UUID customerID;
    private final String type = "customer.cart.create";
}
