package org.restbucks.shopping.application.port.in

import org.restbucks.shopping.application.EventPublisher
import org.restbucks.shopping.application.cart.item.add.ItemAddedToCustomerCartEvent
import org.restbucks.shopping.application.common.message.Command
import spock.lang.Specification

import static java.util.UUID.randomUUID
import static org.restbucks.shopping.application.port.in.ShoppingTestContext.*

class AddingItemToCustomerCartTest extends Specification {

    def publisher = Mock(EventPublisher)
    def facade = ShoppingTestContext.facade(publisher)

    def 'should publish event when adding item to customer cart'() {

        given: 'item was added to offer and customer cart was created'
        facade.accept(itemAndCartAvailable)

        when: 'adding item to customer cart'
        facade.accept(addItemToCustomerCart(customerID, itemID))

        then: 'correct event should be published'
        1 * publisher.publish({ it.customerID == customerID && it.itemID == itemID })

        where:
        customerID   | itemID       | quantity      | itemAndCartAvailable
        randomUUID() | randomUUID() | anyQuantity() | [createCart(customerID), addItemToOffer(itemID)] as Command[]
    }

    def 'should not allow adding item out of offer to customer cart'() {

        given: 'customer cart was created'
        facade.accept(createCart(customerID))

        when: 'adding item out of offer to customer cart'
        facade.accept(addItemToCustomerCart(customerID, itemID))

        then: 'exception should be thrown'
        thrown NoSuchElementException

        where:
        customerID   | itemID
        randomUUID() | randomUUID()
    }

    def 'should not allow adding item if cart was not created'() {

        given: 'item added to offer'
        facade.accept(addItemToOffer(itemID))

        when: 'adding item out of offer to customer cart'
        facade.accept(addItemToCustomerCart(customerID, itemID))

        then: 'exception should be thrown'
        thrown NoSuchElementException

        where:
        customerID   | itemID
        randomUUID() | randomUUID()
    }
}
