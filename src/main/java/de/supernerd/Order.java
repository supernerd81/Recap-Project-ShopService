package de.supernerd;

import lombok.With;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

public record Order(
        String id,
        List<Product> products,
        @With OrderStatus orderStatus,
        ZonedDateTime timestamp
) {
}
