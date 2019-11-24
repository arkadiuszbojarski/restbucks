package org.restbucks.shopping.application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.restbucks.shopping.application.common.CommandHandler;
import org.restbucks.shopping.application.common.message.Command;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
public class ShoppingFacade {

    private static final String NULL_COMMAND_FOUND = "Command cannot be null but null found in %s";

    @NonNull private final List<CommandHandler> handlers;

    private CommandHandler getHandler(Command command) {
        final var commandClass = command.getClass();
        final var handler = handlers.stream()
            .filter(h -> h.supported().equals(commandClass))
            .findFirst()
            .orElseThrow();

        return handler;
    }

    public void accept(final Command... commands) {
        requireNonNull(commands);
        requireAllNonNull(commands);
        stream(commands).forEach(this::accept);
    }

    private void requireAllNonNull(Command... commands) {
        final Supplier<String> supplier = () -> format(NULL_COMMAND_FOUND, commands);
        if (anyNull(commands)) throw new IllegalArgumentException(supplier.get());
    }

    private boolean anyNull(Command... commands) {
        return stream(commands).anyMatch(Objects::isNull);
    }

    private void accept(Command command) {
        getHandler(command).accept(command);
    }
}
