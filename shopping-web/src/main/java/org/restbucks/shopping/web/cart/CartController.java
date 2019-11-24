package org.restbucks.shopping.web.cart;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.restbucks.shopping.application.ShoppingFacade;
import org.restbucks.shopping.application.cart.item.add.AddItemToCustomerCartCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController {

    @NonNull private final ShoppingFacade facade;

    @PutMapping(
        value = "/shopping/customers/{customerID}/cart/items/{itemID}",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addItemToCustomerCart(
        @PathVariable("customerID") final UUID customerID,
        @PathVariable("itemID") final UUID itemID,
        @Valid @RequestBody final ItemQuantityDTO dto) {

        final var command = AddItemToCustomerCartCommand.of(randomUUID(), customerID, itemID, dto.getQuantity());
        facade.accept(command);

        return ResponseEntity.ok().build();
    }
}
