package org.restbucks.shopping.domain.cart;

import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
public class Cart {
    @NonNull private final UUID customerID;
    @NonNull private final List<UUID> items = new ArrayList<>();
}
