package com.alexcode.eatgo.application.exceptions;

import com.alexcode.eatgo.exceptions.ErrorCode;
import com.alexcode.eatgo.exceptions.InvalidValueException;

public class CategoryDuplicationException extends InvalidValueException {

    public CategoryDuplicationException(String name) {
        super(name, ErrorCode.CATEGORY_DUPLICATION);
    }
}
