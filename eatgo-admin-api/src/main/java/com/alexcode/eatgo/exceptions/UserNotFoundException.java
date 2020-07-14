package com.alexcode.eatgo.exceptions;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(Long id) {
        super("Could not find region with id : " + id, ErrorCode.ENTITY_NOT_FOUND);
    }
}
