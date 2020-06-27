package com.alexcode.eatgo.exceptions;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
