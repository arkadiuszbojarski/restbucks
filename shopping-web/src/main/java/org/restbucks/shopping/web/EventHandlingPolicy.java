package org.restbucks.shopping.web;

import org.restbucks.shopping.application.common.message.Event;

import java.util.function.Consumer;

public interface EventHandlingPolicy<E extends Event> extends Consumer<E> {

}
