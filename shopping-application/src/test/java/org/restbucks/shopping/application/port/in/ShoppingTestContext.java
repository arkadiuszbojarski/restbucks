package org.restbucks.shopping.application.port.in;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.restbucks.shopping.application.EventPublisher;
import org.restbucks.shopping.application.ShoppingFacade;
import org.restbucks.shopping.application.cart.item.add.AddItemToCustomerCartCommand;
import org.restbucks.shopping.application.cart.item.add.AddingItemToCustomerCartHandler;
import org.restbucks.shopping.application.cart.item.remove.RemoveItemFromCustomerCartCommand;
import org.restbucks.shopping.application.cart.item.remove.RemovingItemFromCustomerCartHandler;
import org.restbucks.shopping.application.common.CommandHandler;
import org.restbucks.shopping.application.customer.CreateCartForCustomerCommand;
import org.restbucks.shopping.application.customer.CustomerCartCreationHandler;
import org.restbucks.shopping.application.item.AddItemToOfferCommand;
import org.restbucks.shopping.application.item.AddingItemToOfferHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.UUID.randomUUID;

class ShoppingTestContext {

    private static final RandomDataGenerator GENERATOR = new RandomDataGenerator();

    public static String anyCurrency() {
        return "PLN";
    }

    public static BigDecimal anyPrice() {
        final var uniform = GENERATOR.nextUniform(1.0, 10000.0);
        final var value = new BigDecimal(uniform);

        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public static int anyQuantity() {
        return GENERATOR.nextInt(1, 1000);
    }

    public static RemoveItemFromCustomerCartCommand removeItemFromCart(UUID customerID, UUID itemID) {
        return RemoveItemFromCustomerCartCommand.of(customerID, itemID);
    }

    public static AddItemToCustomerCartCommand addItemToCustomerCart(UUID customerID, UUID itemID) {
        return AddItemToCustomerCartCommand.of(randomUUID(), customerID, itemID, anyQuantity());
    }

    public static AddItemToOfferCommand addItemToOffer(UUID itemID) {
        return AddItemToOfferCommand.of(randomUUID(), itemID, anyPrice(), anyCurrency());
    }

    private static CreateCartForCustomerCommand createCart(UUID customerID) {
        return CreateCartForCustomerCommand.of(randomUUID(), customerID);
    }

    private static ShoppingFacade configureShoppingFacade(EventPublisher publisher) {
        final var cartRepository = new InMemoryCartRepository();
        final var itemRepository = new InMemoryItemRepository();

        final List<CommandHandler> handlers = asList(
            new CustomerCartCreationHandler(cartRepository),
            new AddingItemToOfferHandler(itemRepository),
            new AddingItemToCustomerCartHandler(publisher, cartRepository, itemRepository),
            new RemovingItemFromCustomerCartHandler(publisher, cartRepository)
        );

        return new ShoppingFacade(handlers);
    }

    private ShoppingTestContext() {
    }

    public static ShoppingFacade facade(EventPublisher publisher) {
        return configureShoppingFacade(publisher);
    }
}
