package contracts.rest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'should add item to customer cart, given customer visited restbucks and item was added to offer'
    request {
        method 'PUT'
        headers {
            contentType('application/json;charset=utf-8')
        }
        url '/shopping/customers/00570c06-38a0-43a1-a690-d3792fe83b36/cart/items/489e9fbc-3675-4910-b684-b02b31930d52'
        body(quantity: 1)
    }
    response { status(200) }
}