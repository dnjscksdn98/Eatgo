package com.alexcode.eatgo.exceptions;

public class RegionDuplicationException extends InvalidValueException {

    public RegionDuplicationException(String name) {
        super(name, ErrorCode.REGION_DUPLICATION);
    }
}
