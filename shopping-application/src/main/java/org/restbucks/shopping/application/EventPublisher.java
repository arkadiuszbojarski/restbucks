package org.restbucks.shopping.application;

import org.restbucks.shopping.application.common.message.Event;

public interface EventPublisher {
    void publish(Event event);
}
