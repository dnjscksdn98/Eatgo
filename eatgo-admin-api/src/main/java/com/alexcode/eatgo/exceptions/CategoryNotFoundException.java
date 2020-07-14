package com.alexcode.eatgo.exceptions;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException(Long id) {
        super("Could not find category with id : " + id, ErrorCode.ENTITY_NOT_FOUND);
    }
}
