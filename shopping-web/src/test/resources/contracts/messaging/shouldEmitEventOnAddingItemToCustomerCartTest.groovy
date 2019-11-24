package contracts.messaging

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'should emmit event when item added to customer cart'
    label 'customer.cart.item.added'
    input {
        triggeredBy('addItemToCustomerCart()')
    }
    outputMessage {
        sentTo'output'
        headers {
            header('contentType', applicationJson())
            header('type', 'customer.cart.item.added')
        }
        body([
                eventID: $(consumer(anyUuid()), producer(uuid())),
                customerID: $(consumer(anyUuid()), producer(uuid())),
                itemID: $(consumer(anyUuid()), producer(uuid())),
                quantity: '1',
                type: 'customer.cart.item.added'
        ])
    }
}
