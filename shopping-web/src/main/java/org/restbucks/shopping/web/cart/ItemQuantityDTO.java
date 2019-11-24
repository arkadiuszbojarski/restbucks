package org.restbucks.shopping.web.cart;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class ItemQuantityDTO {
    @Min(0)
    @NotNull
    private final Integer quantity;
}
