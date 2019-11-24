package org.restbucks.shopping.web.customer;

import lombok.RequiredArgsConstructor;
import org.restbucks.shopping.application.ShoppingFacade;
import org.restbucks.shopping.application.customer.CreateCartForCustomerCommand;
import org.restbucks.shopping.web.EventHandlingPolicy;
import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
@RequiredArgsConstructor
class CustomerVisitHandlingPolicy implements EventHandlingPolicy<CustomerVisitedEvent> {

    private final ShoppingFacade facade;

    @Override
    public void accept(final CustomerVisitedEvent event) {
        final var customerID = event.getCustomerID();

        facade.accept(CreateCartForCustomerCommand.of(randomUUID(), customerID));
    }
}
