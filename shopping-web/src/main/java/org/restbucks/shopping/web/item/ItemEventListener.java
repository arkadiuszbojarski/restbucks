package org.restbucks.shopping.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Sink.class)
@RequiredArgsConstructor
public class ItemEventListener {

    private final ItemAddedToOfferHandlingPolicy policy;

    @StreamListener(value = Sink.INPUT, condition = "headers['type'] == 'item.add.to.offer'")
    public void onItemAddedToOffer(final ItemAddedToOfferEvent event) {
        policy.accept(event);
    }
}
