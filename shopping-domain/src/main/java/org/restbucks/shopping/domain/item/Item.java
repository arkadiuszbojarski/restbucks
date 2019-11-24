package org.restbucks.shopping.domain.item;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class Item {
    @NonNull private final UUID itemID;
}
