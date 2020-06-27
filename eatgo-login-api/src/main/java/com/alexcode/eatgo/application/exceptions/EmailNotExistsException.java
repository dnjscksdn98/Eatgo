package com.alexcode.eatgo.application.exceptions;

import com.alexcode.eatgo.exceptions.EntityNotFoundException;
import com.alexcode.eatgo.exceptions.ErrorCode;

public class EmailNotExistsException extends EntityNotFoundException {

  public EmailNotExistsException() {
    super(ErrorCode.INVALID_LOGIN_VALUE);
  }
}
