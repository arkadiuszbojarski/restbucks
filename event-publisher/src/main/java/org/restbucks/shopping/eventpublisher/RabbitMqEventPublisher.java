package org.restbucks.shopping.eventpublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.restbucks.shopping.application.common.message.Event;
import org.restbucks.shopping.application.EventPublisher;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.messaging.support.MessageBuilder.createMessage;

@Slf4j
@Component
@EnableBinding(Source.class)
@RequiredArgsConstructor
public class RabbitMqEventPublisher implements EventPublisher {

    private static final String TYPE_HEADER = "type";
    private final Source source;

    @Override
    public void publish(final Event event) {
        final var headers = createHeaders(event);
        final var message = createMessage(event, headers);

        source.output().send(message);
    }

    private static MessageHeaders createHeaders(final Event event) {
        return new MessageHeaders(Map.of(TYPE_HEADER, event.getType()));
    }
}
