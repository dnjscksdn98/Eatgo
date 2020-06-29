package com.alexcode.eatgo.application.exceptions;

import com.alexcode.eatgo.exceptions.ErrorCode;
import com.alexcode.eatgo.exceptions.InvalidValueException;

public class RegionDuplicationException extends InvalidValueException {

    public RegionDuplicationException(String name) {
        super(name, ErrorCode.REGION_DUPLICATION);
    }
}
