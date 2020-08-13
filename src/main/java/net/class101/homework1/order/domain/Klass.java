package net.class101.homework1.order.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Klass {
    private final String name;
    private final double price;
    private final int quantity;
}
