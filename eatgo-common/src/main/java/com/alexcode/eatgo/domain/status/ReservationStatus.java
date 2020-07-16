package com.alexcode.eatgo.domain.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {

    WAITING(0, "WAITING", "Waiting for the reservation"),
    ACCEPTED(1, "ACCEPTED", "Owner accepted the reservation"),
    REFUSED(2, "REFUSED", "Owner refused the reservation");

    private final Integer id;
    private final String title;
    private final String description;

}
