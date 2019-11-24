package org.restbucks.shopping.application.port.in

import org.restbucks.shopping.application.EventPublisher
import org.restbucks.shopping.application.cart.item.remove.ItemRemovedFromCustomerCartEvent
import org.restbucks.shopping.application.common.message.Command
import spock.lang.Specification

import static java.util.UUID.randomUUID
import static org.restbucks.shopping.application.port.in.ShoppingTestContext.*

class RemovingItemFromCustomerCartTest extends Specification {

    def publisher = Mock(EventPublisher)
    def facade = ShoppingTestContext.facade(publisher)

    def 'should publish event when removing item from customer cart'() {

        given: 'item in customer cart'
        facade.accept(itemInCustomerCart)

        when: 'removing item from customer cart'
        facade.accept(removeItemFromCart(customerID, itemID))

        then: 'should publish correct event'
        1 * publisher.publish({ it.customerId == customerID && it.itemID == itemID })

        where:
        customerID   | itemID       | itemInCustomerCart
        randomUUID() | randomUUID() | [createCart(customerID), addItemToOffer(itemID), addItemToCustomerCart(customerID, itemID)] as Command[]
        randomUUID() | randomUUID() | [createCart(customerID), addItemToOffer(itemID), addItemToCustomerCart(customerID, itemID), addItemToCustomerCart(customerID, itemID)] as Command[]
        randomUUID() | randomUUID() | [createCart(customerID), addItemToOffer(itemID), addItemToCustomerCart(customerID, itemID), removeItemFromCart(customerID, itemID), addItemToCustomerCart(customerID, itemID)] as Command[]
    }

    def 'should not publish event when removing item that is not in customer cart'() {

        given: 'item not in customer cart'
        facade.accept(itemNotInCustomerCart)

        when: 'removing item from customer cart'
        facade.accept(removeItemFromCart(customerID, itemID))

        then: 'should not publish event'
        0 * publisher.publish(_)

        where:
        customerID   | itemID       | itemNotInCustomerCart
        randomUUID() | randomUUID() | [createCart(customerID), addItemToOffer(itemID)] as Command[]
    }
}
