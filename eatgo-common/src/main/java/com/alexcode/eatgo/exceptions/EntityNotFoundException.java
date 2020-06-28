package com.alexcode.eatgo.exceptions;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EntityNotFoundException(final String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
