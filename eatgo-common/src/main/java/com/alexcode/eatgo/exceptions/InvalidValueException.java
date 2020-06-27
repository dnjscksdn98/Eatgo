package com.alexcode.eatgo.exceptions;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
