package com.alexcode.eatgo.exceptions;

public class CategoryDuplicationException extends InvalidValueException {

    public CategoryDuplicationException(String name) {
        super(name, ErrorCode.CATEGORY_DUPLICATION);
    }
}
