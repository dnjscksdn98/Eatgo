package com.alexcode.eatgo.domain.exceptions;

import com.alexcode.eatgo.exceptions.ErrorCode;
import com.alexcode.eatgo.exceptions.InvalidValueException;

public class EmailDuplicationException extends InvalidValueException {

    public EmailDuplicationException(final String email) {
        super(email, ErrorCode.EMAIL_DUPLICATION);
    }
}
