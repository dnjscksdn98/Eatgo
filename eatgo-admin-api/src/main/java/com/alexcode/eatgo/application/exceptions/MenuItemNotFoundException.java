package com.alexcode.eatgo.application.exceptions;

import com.alexcode.eatgo.exceptions.EntityNotFoundException;
import com.alexcode.eatgo.exceptions.ErrorCode;

public class MenuItemNotFoundException extends EntityNotFoundException {

    public MenuItemNotFoundException(Long id) {
        super("Could not find menu with id : " + id, ErrorCode.ENTITY_NOT_FOUND);
    }
}
