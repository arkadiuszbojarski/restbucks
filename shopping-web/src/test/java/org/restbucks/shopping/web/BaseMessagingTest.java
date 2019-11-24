package org.restbucks.shopping.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.restbucks.shopping.application.common.message.Event;
import org.restbucks.shopping.web.cart.CartController;
import org.restbucks.shopping.web.cart.ItemQuantityDTO;
import org.restbucks.shopping.web.customer.CustomerVisitedEvent;
import org.restbucks.shopping.web.item.ItemAddedToOfferEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMessageVerifier
public abstract class BaseMessagingTest {

    @Autowired
    private CartController controller;

    @Autowired
    private Sink channels;

    @Autowired
    private ObjectMapper mapper;

    private final UUID customerID = randomUUID();
    private final UUID itemID = randomUUID();

    @Before
    public void setup() throws JsonProcessingException {
        customerVisited();
        itemAddedToOffer();
    }

    private void customerVisited() throws JsonProcessingException {
        final var event = CustomerVisitedEvent.of(
            randomUUID(),
            customerID);

        receiveEvent(event);
    }

    private void itemAddedToOffer() throws JsonProcessingException {
        final var event = ItemAddedToOfferEvent.of(
            randomUUID(),
            itemID,
            "LATTE",
            new BigDecimal("2.50"),
            "PLN");

        receiveEvent(event);
    }

    public void addItemToCustomerCart() {
        controller.addItemToCustomerCart(customerID, itemID, ItemQuantityDTO.of(1));
    }

    private boolean receiveEvent(final Event event) throws JsonProcessingException {
        return channels.input().send(prepareMessage(event));
    }

    private static MessageHeaders prepareHeaders(final Event event) {
        return new MessageHeaders(Map.of("type", event.getType()));
    }

    private GenericMessage<?> prepareMessage(final Event event) throws JsonProcessingException {
        return new GenericMessage<>(mapper.writeValueAsString(event), prepareHeaders(event));
    }
}
