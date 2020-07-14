package com.alexcode.eatgo.exceptions;

public class RegionNotFoundException extends EntityNotFoundException {

    public RegionNotFoundException(Long id) {
        super("Could not find region with id : " + id, ErrorCode.ENTITY_NOT_FOUND);
    }
}
