package com.alexcode.eatgo.exceptions;

public class MenuItemNotFoundException extends EntityNotFoundException {

    public MenuItemNotFoundException(Long id) {
        super("Could not find menu with id : " + id, ErrorCode.ENTITY_NOT_FOUND);
    }
}
