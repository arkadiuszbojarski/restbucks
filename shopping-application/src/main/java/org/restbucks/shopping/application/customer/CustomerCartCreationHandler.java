package org.restbucks.shopping.application.customer;

import lombok.RequiredArgsConstructor;
import org.restbucks.shopping.domain.cart.Cart;
import org.restbucks.shopping.domain.cart.CartRepository;
import org.restbucks.shopping.application.common.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerCartCreationHandler implements CommandHandler<CreateCartForCustomerCommand> {

    private final CartRepository repository;

    @Override
    public Class<CreateCartForCustomerCommand> supported() {
        return CreateCartForCustomerCommand.class;
    }

    @Override
    public void accept(final CreateCartForCustomerCommand command) {
        repository.save(new Cart(command.getCustomerID()));
    }
}
