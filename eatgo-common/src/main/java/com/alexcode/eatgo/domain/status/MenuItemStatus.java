package com.alexcode.eatgo.domain.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuItemStatus {

    REGISTERED(0, "REGISTERED", "Menu item registered"),
    UNREGISTERED(1, "UNREGISTERED", "Menu item unregistered");

    private final Integer id;
    private final String title;
    private final String description;
}
