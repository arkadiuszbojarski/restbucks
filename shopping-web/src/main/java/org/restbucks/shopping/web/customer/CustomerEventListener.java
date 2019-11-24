package org.restbucks.shopping.web.customer;

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
public class CustomerEventListener {

    private final CustomerVisitHandlingPolicy policy;

    @StreamListener(value = Sink.INPUT, condition ="headers['type'] == 'customer.visited'" )
    public void onCustomerVist(final CustomerVisitedEvent event) {
        policy.accept(event);
    }
}
