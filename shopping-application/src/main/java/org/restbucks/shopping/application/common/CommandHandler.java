package org.restbucks.shopping.application.common;

import org.restbucks.shopping.application.common.message.Command;

import java.util.function.Consumer;

public interface CommandHandler<C extends Command> extends Consumer<C> {
    Class<C> supported();
}
