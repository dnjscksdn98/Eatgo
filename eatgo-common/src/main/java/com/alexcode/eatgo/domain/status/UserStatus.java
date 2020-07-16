package com.alexcode.eatgo.domain.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED(0, "REGISTERED", "User registered"),
    UNREGISTERED(1, "UNREGISTERED", "User deactivated");

    private final Integer id;
    private final String title;
    private final String description;
}
