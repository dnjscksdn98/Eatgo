package com.alexcode.eatgo.domain.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestaurantStatus {

    REGISTERED(0, "REGISTERED", "Restaurant registered"),
    UNREGISTERED(1, "UNREGISTERED", "Restaurant unregistered");

    private final Integer id;
    private final String title;
    private final String description;
}
