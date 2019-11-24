package org.restbucks.shopping.web.customer;

import lombok.Value;
import org.restbucks.shopping.application.common.message.Event;

import java.util.UUID;

@Value(staticConstructor = "of")
public class CustomerVisitedEvent implements Event {
    private final UUID eventID;
    private final UUID customerID;
    private final String type = "customer.visited";
}
