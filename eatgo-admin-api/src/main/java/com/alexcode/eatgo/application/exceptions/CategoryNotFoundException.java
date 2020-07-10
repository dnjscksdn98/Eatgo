package com.alexcode.eatgo.application.exceptions;

import com.alexcode.eatgo.exceptions.EntityNotFoundException;
import com.alexcode.eatgo.exceptions.ErrorCode;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException(Long id) {
        super("Could not find category with id : " + id, ErrorCode.ENTITY_NOT_FOUND);
    }
}
