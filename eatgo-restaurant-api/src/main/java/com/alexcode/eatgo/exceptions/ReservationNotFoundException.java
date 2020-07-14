package com.alexcode.eatgo.exceptions;

public class ReservationNotFoundException extends EntityNotFoundException {

    public ReservationNotFoundException(Long id) {
        super("Could not find reservation with id : " + id, ErrorCode.ENTITY_NOT_FOUND);
    }
}
